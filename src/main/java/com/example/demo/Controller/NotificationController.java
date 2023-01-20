/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.Notifications;
import com.example.demo.entity.Reunion;
import com.example.demo.entity.User;
import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.response.JwtResponse;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.models.JwtProvider;
import com.example.demo.models.RoleName;
import com.example.demo.repository.RoleRepository;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Session;
import com.example.demo.repository.NotificationsRepository;
import com.example.demo.repository.ReunionRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;
import java.time.LocalDate;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UtilisateurRepository;
//import com.example.demo.security.jwt.JwtProvider;

//import com.example.demo.util.RoleEnum;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


/**
 *
 * @author Casimir
 */
@RestController
@RequestMapping("/admin/notification")
public class NotificationController {
    
    @Autowired
    NotificationsRepository notificationsRepository;

//    @PostMapping("/new/{id}")
//    public ResponseEntity<?> createSession(@RequestBody Session session, @PathVariable Long id) { 
////        Long x = Long.parseLong("1");
//        Reunion reunion = reunionRepository.findById(id).get();
//        System.out.println("reunion: "+ reunion.getIdReunion());
//        session.setReunion(reunion);
//        System.out.println("session: "+ session.getDebut());
//        if (session.getParticipants() > userRepository.Count()){
//            return new ResponseEntity<>(new ResponseMessage("Attention! -> le nombre de cotisant ne peut être supérieur au nombre de membre"),
//              HttpStatus.BAD_REQUEST);
//        }
//        sessionRepository.save(session);
//      return ResponseEntity.ok(session);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateSession(@Valid @RequestBody Session session, Long id) {        
//       Reunion reunion = reunionRepository.findById(id).get();
//        System.out.println("reunion: "+ reunion.getFondateur());
//        session.setReunion(reunion);
//        System.out.println("session: "+ session.getDebut());
//        sessionRepository.save(session);
//      return new ResponseEntity<>(session,HttpStatus.ACCEPTED);
//    }

    @GetMapping
    public List<JSONObject> getSession(){      
        
        return notificationsRepository.getAll(LocalDate.now().minusDays(30),LocalDate.now());
    }

//    @GetMapping("/active")
//    public JSONObject getActiveSession(){      
//        return sessionRepository.getActiveSession();
//    }
//
//    @GetMapping("/all")
//    public List<JSONObject> getAllSession(){      
//        return sessionRepository.getAllSession();
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteSession(@PathVariable Long id) {
//            sessionRepository.delete(sessionRepository.findById(id).get());
//    }
        
}
