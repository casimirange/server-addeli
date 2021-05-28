/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.Communique;
import com.example.demo.entity.CompteRendu;
import com.example.demo.entity.Notifications;
import com.example.demo.entity.Prets;
import com.example.demo.entity.User;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.entity.Session;
import com.example.demo.entity.Tontine;
import com.example.demo.repository.CommuniqueRepository;
import com.example.demo.repository.CompteRenduRepository;
import com.example.demo.repository.PretRepository;
import com.example.demo.repository.RetenueRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TontineRepository;
import com.example.demo.repository.UserRepository;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UtilisateurRepository;
//import com.example.demo.security.jwt.JwtProvider;

//import com.example.demo.util.RoleEnum;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.repository.NotificationsRepository;
import org.springframework.web.bind.annotation.PutMapping;


/**
 *
 * @author Casimir
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/communique")
public class CommuniqueController {
           
    @Autowired
    SessionRepository sessionRepository;
        
    @Autowired
    NotificationsRepository notificationsRepository; 
    
    @Autowired
    CommuniqueRepository communiqueRepository;        
    
    JSONObject json;
    String mts;
    
    @PostMapping("")
    public ResponseEntity<?> createCR(@RequestBody Communique communique) {         
        List<Session> sess = sessionRepository.findByEtat(true);
        Session session = sess.get(0);
        communique.setSession(session);
        communique.setDate(LocalDate.now());        
        
        Notifications notifications = new Notifications();
        notifications.setDescription("un nouveau communiqué a été mis en ligne en date du "+communique.getDate());
        notifications.setDate(LocalDate.now());
                
        notificationsRepository.save(notifications);
        communiqueRepository.save(communique);
        return new ResponseEntity<>(new ResponseMessage("communiqué enregistré!"), HttpStatus.CREATED); 
    } 

    @GetMapping("")
    public List<JSONObject> getPrets(){      
        return communiqueRepository.findCommunique();
    }
        
}
