/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.Planing;
import com.example.demo.entity.Reunion;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.entity.Session;
import com.example.demo.repository.PlanningRepository;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/planing")
public class PlanningController {
        
    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    PlanningRepository planningRepository;

    @PostMapping()
    public ResponseEntity<?> createSession(@RequestBody Planing planing) { 

        List<Session> sess = sessionRepository.findByEtat(true);
        Session session = sess.get(0);
        
//        planing.setDate(planing.getDate());
        planing.setSession(session);
        planing.setEtat(false);
        
        if (planing.getDate().isBefore(session.getDebut())){
            return new ResponseEntity<>(new ResponseMessage("Erreur! -> La date ne peut être définié avant la période de session"),
              HttpStatus.BAD_REQUEST);
        }
        
        if (planing.getDate().isAfter(session.getFin())){
            return new ResponseEntity<>(new ResponseMessage("Erreur! -> La date ne peut être définié après la période de session"),
              HttpStatus.BAD_REQUEST);
        }
        
        if (planningRepository.existsByDate(planing.getDate())){
            return new ResponseEntity<>(new ResponseMessage("Erreur! -> Cette date a déjà été enregistré au cours de cette session"),
              HttpStatus.BAD_REQUEST);
        }
        
        if (planningRepository.countBySession(session) == 12){
            return new ResponseEntity<>(new ResponseMessage("Attention! -> Vous avez déjà atteint le nombre maximal d'enregistrement"),
              HttpStatus.BAD_REQUEST);
        }
        planningRepository.save(planing);
      return new ResponseEntity<>(new ResponseMessage("mise à jour planning"),
              HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSession(@Valid @RequestBody Session session, Long id) {        
//       Reunion reunion = reunionRepository.findById(id).get();
//        System.out.println("reunion: "+ reunion.getFondateur());
//        session.setReunion(reunion);
//        System.out.println("session: "+ session.getDebut());
//        sessionRepository.save(session);
      return new ResponseEntity<>(session,HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<Session> getSession(){      
        return sessionRepository.findAll();
    }

    @GetMapping("/active")
    public List<Session> getActiveSession(){      
        return sessionRepository.findByEtat(true);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
            sessionRepository.delete(sessionRepository.findById(id).get());
    }
        
}
