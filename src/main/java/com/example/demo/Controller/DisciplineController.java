/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.Amande;
import com.example.demo.entity.Discipline;
import com.example.demo.entity.Notifications;
import com.example.demo.entity.User;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.entity.Session;
import com.example.demo.repository.AmandeRepository;
import com.example.demo.repository.DisciplineRepository;
import com.example.demo.repository.RetenueRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TontineRepository;
import com.example.demo.repository.UserRepository;
import java.time.LocalDate;
import java.util.HashMap;
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
@RequestMapping("/admin/discipline")
public class DisciplineController {
           
    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    DisciplineRepository disciplineRepository;
    
    @Autowired
    NotificationsRepository notificationsRepository;       
    
    JSONObject json;
    String mts;
    
    @PostMapping("/{id}")
    public ResponseEntity<?> createAmande(@PathVariable Long id, @RequestBody Discipline discipline) { 
        User user = userRepository.findById(id).get(); 
        List<Session> sess = sessionRepository.findByEtat(true);
        
        Session session = sess.get(0);
        discipline.setSession(session);
        discipline.setUser(user);        
        
        Notifications notifications = new Notifications();
        if(discipline.getType().equals("retard")){
            notifications.setDescription(user.getName()+" est venu en retard le "+discipline.getDate());
        }else{
            notifications.setDescription(user.getName()+" ne s'est pas pointé à la séance du "+discipline.getDate());
        }
        
        notifications.setDate(LocalDate.now());    
        
        notificationsRepository.save(notifications);      
        disciplineRepository.save(discipline);
        return new ResponseEntity<>(new ResponseMessage("discipline mise à jour"), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<JSONObject> getActiveSessionTontine(){
        return disciplineRepository.findDiscipline();
    }
        
}
