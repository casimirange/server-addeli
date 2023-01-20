/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.Reunion;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.entity.Session;
import com.example.demo.repository.ReunionRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UtilisateurRepository;
//import com.example.demo.security.jwt.JwtProvider;

//import com.example.demo.util.RoleEnum;
import java.util.List;
import javax.validation.Valid;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


/**
 *
 * @author Casimir
 */
@RestController
@RequestMapping("/admin/session")
public class SessionController {
    
    @Autowired
    ReunionRepository reunionRepository;
    
    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/new")
    public ResponseEntity<?> createSession(@RequestBody Session session) { 
        Long x = Long.parseLong("1");
        Reunion reunion = reunionRepository.findById(x).get();
        System.out.println("reunion: "+ reunion.getIdReunion());
        session.setReunion(reunion);
        System.out.println("session: "+ session.getDebut());
        session.setEtat(true);
        session.setEncours(true);
        session.setDuree(12);
        session.setTaux(session.getTaux());
        Session s = sessionRepository.findFirstByOrderByIdSessionDesc();
        if(s != null){           
            if (session.getDebut().isBefore(s.getFin())){
                return new ResponseEntity<>(new ResponseMessage("Erreur! -> La nouvelle session ne peut débuter avant la fin de la précédente"),
                  HttpStatus.BAD_REQUEST);
            }
        }
        if (session.getParticipants() > userRepository.Count()){
            return new ResponseEntity<>(new ResponseMessage("Attention! -> le nombre de cotisant ne peut être supérieur au nombre de membre"),
              HttpStatus.BAD_REQUEST);
        }
        System.out.println("liste de session active : "+ sessionRepository.findByEtat(true).size());
        if (!sessionRepository.findByEtat(true).isEmpty()){
            return new ResponseEntity<>(new ResponseMessage("Attention! -> Une session est déjà en cours. Veuillez teminer la précédente avant"
                    + " de créér une nouvelle"),
              HttpStatus.BAD_REQUEST);
        }
        sessionRepository.save(session);
      return ResponseEntity.ok(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSession(@Valid @RequestBody Session session, Long id) {        
       Reunion reunion = reunionRepository.findById(id).get();
        System.out.println("reunion: "+ reunion.getFondateur());
        session.setReunion(reunion);
        System.out.println("session: "+ session.getDebut());
        sessionRepository.save(session);
      return new ResponseEntity<>(session,HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<Session> getSession(){      
        return sessionRepository.findAll();
    }

    @GetMapping("/active")
    public JSONObject getActiveSession(){      
        return sessionRepository.getActiveSession();
    }

    @GetMapping("/all")
    public List<JSONObject> getAllSession(){      
        return sessionRepository.getAllSession();
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
            sessionRepository.delete(sessionRepository.findById(id).get());
    }   
    
    @PutMapping("/disable/{id}")
    public ResponseEntity<?> enableDesableUser(@PathVariable Long id) {
        Session u = sessionRepository.findById(id).get();
        if(u.isEtat()){
            u.setEtat(false);
            u.setEncours(false);
        }else{
            u.setEtat(true);
            u.setEncours(true);
        }
        sessionRepository.save(u);
        return new ResponseEntity<>("Session désactivée",HttpStatus.OK);
    }
}
