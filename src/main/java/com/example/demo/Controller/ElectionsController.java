/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.Amande;
import com.example.demo.entity.Elections;
import com.example.demo.entity.Notifications;
import com.example.demo.entity.User;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.entity.Session;
import com.example.demo.repository.AmandeRepository;
import com.example.demo.repository.ElectionRepository;
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
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Casimir
 */
@RestController
@RequestMapping("/admin/elections")
public class ElectionsController {
           
    @Autowired
    TontineRepository tontineRepository;
    
    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ElectionRepository electionRepository;
    
    @Autowired
    AmandeRepository amandeRepository;
    
    @Autowired
    NotificationsRepository notificationsRepository;       
    
    LocalDate date1, date2, date ;
    String mts, j, l, n  ;
    JSONObject json, json2;
    
    @PostMapping()
    public ResponseEntity<?> createBureau(@RequestBody Elections elections) { 
        List<Session> sess = sessionRepository.findByEtat(true);        
        Session session = sess.get(0);
        Long c = Long.parseLong(elections.getUser());
        User u = userRepository.findById(c).get();
        Long t = Long.parseLong(u.getTel());
        elections.setTel(t);
        
        if (electionRepository.existsByUserAndSession(u.getName(), session)) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> User is already exist in this session!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if(!elections.getFonction().equals("Adhérant")){
            if (electionRepository.existsByFonctionAndSession(elections.getFonction(), session)) {
            return new ResponseEntity<>(new ResponseMessage("Ce poste est déjà occupé dans la réunion"),
                    HttpStatus.BAD_REQUEST);
            }
        }
        
        elections.setSession(session);
        elections.setUser(u.getName());
        
        Notifications notifications = new Notifications();
        notifications.setDescription(elections.getUser() +" est désormais "+elections.getFonction()+ " de la réunion");
        notifications.setDate(LocalDate.now());    
        electionRepository.save(elections);
        notificationsRepository.save(notifications);      
        
        return new ResponseEntity<>(new ResponseMessage("bureau mis à jours"), HttpStatus.CREATED);
    }
    
    @PutMapping()
    public ResponseEntity<?> updateBureau(@RequestParam("user") User u, @RequestParam("id") Long id, @RequestBody Elections e) { 
        User user = userRepository.findById(u.getId()).get(); 
        List<Session> sess = sessionRepository.findByEtat(true);
        Elections elections = electionRepository.findById(id).get();
        Session session = sess.get(0);
        elections.setSession(session);
        elections.setUser(user.getName());        
        elections.setFonction(e.getFonction());        
        elections.setMontant(e.getMontant());        
//        elections.setDate(disc.getDate());    
        
        System.out.println("id: "+elections.getIdElection());
        System.out.println("sess: "+elections.getSession());
        
//        Notifications notifications = new Notifications();
//        if(discipline.getType().equals("Retard")){
//            notifications.setDescription(user.getName()+" est venu en retard le "+discipline.getDate());
//        }else if(discipline.getType().equals("Absence")){
//            notifications.setDescription(user.getName()+" ne s'est pas pointé à la séance du "+discipline.getDate());
//        }else{
//            notifications.setDescription(user.getName()+" a troublé à la séance du "+discipline.getDate());
//        }
//        
//        notifications.setDate(LocalDate.now());    
//        
//        notificationsRepository.save(notifications);      
//        disciplineRepository.save(discipline);
        
        Notifications notifications = new Notifications();
        notifications.setDescription(elections.getUser() +" est désormais "+elections.getFonction()+ " de la réunion");
        notifications.setDate(LocalDate.now());    
        electionRepository.save(elections);
        notificationsRepository.save(notifications);      
        
        return new ResponseEntity<>(new ResponseMessage("bureau mis à jours"), HttpStatus.CREATED);
    }
        
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateBureau(@PathVariable Long id, @RequestBody Amande amande) { 
//        Amande amandes = amandeRepository.findById(id).get();
//        return amandeRepository.findAmande();
//    }


    @GetMapping()
    public List<JSONObject> getActiveSessionTontine(){
        return electionRepository.Bureau();
    }


    @GetMapping("/solde")
    public JSONObject getSoldeAmande(){
        Amande amande = amandeRepository.findFirstByOrderByIdAmandeDesc();
        Map<String, Object> response = new HashMap<>();
        JSONObject solde;
        response.put("solde", amande.getSolde());
        solde = new JSONObject(response);
        return solde;
    }
    
    @GetMapping("/evolution")
    public List<JSONObject> dashLastMonth(){
        List<JSONObject> test = electionRepository.evolution2();
        return test;
    }
    @DeleteMapping("/{id}")
    public void deletePret(@PathVariable Long id) {
            electionRepository.delete(electionRepository.findById(id).get());
    }
        
}
