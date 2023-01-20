/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
import com.example.demo.entity.CompteRendu;
import com.example.demo.entity.Notifications;
import com.example.demo.entity.Prets;
import com.example.demo.entity.User;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.entity.Session;
import com.example.demo.entity.Tontine;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Casimir
 */
@RestController
@RequestMapping("/admin/compteRendu")
public class CompteRenduController {
           
    @Autowired
    TontineRepository tontineRepository;
    
    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RetenueRepository retenueRepository;
    
    @Autowired
    PretRepository pretRepository;
    
    @Autowired
    NotificationsRepository notificationsRepository; 
    
    @Autowired
    CompteRenduRepository compteRenduRepository;        
    
    JSONObject json;
    String mts;
    
    @PostMapping("")
    public ResponseEntity<?> createCR(@RequestBody CompteRendu compteRendu) {         
        List<Session> sess = sessionRepository.findByEtat(true);
        Session session = sess.get(0);
        compteRendu.setSession(session);
        
        
        Notifications notifications = new Notifications();
        notifications.setDescription("Le compte rendu de la séance du "+compteRendu.getDate()+" est déjà disponible");
        notifications.setDate(LocalDate.now());
                
        notificationsRepository.save(notifications);
        compteRenduRepository.save(compteRendu); 
        return new ResponseEntity<>(new ResponseMessage("compte rendu enregistré!"), HttpStatus.CREATED); 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> remboursePret(@PathVariable Long id, @RequestBody Prets prets) { 
        Prets prets2 = pretRepository.findById(id).get();
        Tontine ton = tontineRepository.findFirstByOrderByIdTontineDesc();
        Tontine tontine = new Tontine();
        List<Session> sess = sessionRepository.findByEtat(true);
        Session session = sess.get(0);
        if(prets2.getMontant_prete() > prets.getMontant_rembourse()){
            return new ResponseEntity<>(new ResponseMessage("le montant remboursé est insuffisant "), HttpStatus.CREATED);
        }
        prets2.setRembourse(true);
        prets2.setDateRemboursement(LocalDate.now());
        prets2.setMontant_rembourse(prets.getMontant_rembourse());
        
        
        Notifications notifications = new Notifications(
                prets2.getUser().getName()+" a remboursé son prêt de "+prets.getMontant_rembourse()+" €",
                LocalDate.now());
        
        double solde = ton.getMontant() + prets.getMontant_rembourse();
        tontine.setMontant(solde);        
        tontine.setDebit(prets.getMontant_rembourse());
        tontine.setCredit(0);
        tontine.setMotif("remboursement de prêt par "+prets2.getUser().getName());
        tontine.setUser(prets2.getUser());
        tontine.setDate(LocalDate.now()); 
        tontine.setSession(session); 
        
        pretRepository.save(prets2);
        notificationsRepository.save(notifications);
        tontineRepository.save(tontine);
        return new ResponseEntity<>(new ResponseMessage("prêt remboursé!"), HttpStatus.CREATED);
    } 
    
    @PutMapping("")
    public ResponseEntity<?> updateCR(@RequestBody CompteRendu communique, @RequestParam("id") Long id) {         
        List<Session> sess = sessionRepository.findByEtat(true);
        CompteRendu com = compteRenduRepository.findById(id).get();
        com.setDetails(communique.getDetails());        
        System.out.println("dat : "+com.getDate());
        System.out.println("id : "+com.getIdCompteRendu());
        System.out.println("details : "+com.getDetails());
        Notifications notifications = new Notifications();
        notifications.setDescription("modification du compte rendu du "+com.getDate());
        notifications.setDate(LocalDate.now());
                     
        compteRenduRepository.save(com);
        notificationsRepository.save(notifications);
        return new ResponseEntity<>(new ResponseMessage("compte rendu mis à jour!"), HttpStatus.CREATED); 
    }

    @GetMapping("")
    public List<JSONObject> getPrets(){      
        return compteRenduRepository.findCR();
    }
             
    @DeleteMapping("")
    public void deleteCommunique(@RequestParam("id") Long id) {
        compteRenduRepository.deleteById(id);
    }
        
}
