/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.init;

import com.example.demo.entity.Reunion;
import com.example.demo.entity.User;
import com.example.demo.models.RoleName;
import com.example.demo.repository.RoleRepository;
import com.example.demo.entity.Roles;
import com.example.demo.repository.ReunionRepository;
import com.example.demo.repository.UserRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Casimir
 */

@Component
@Order(3)
public class InitReunion implements ApplicationRunner{
    
      
    @Autowired
    ReunionRepository reunionRepository;
        
    @Override
    public void run(ApplicationArguments args) throws Exception {;
        System.out.println("initialisation de la réunion");        
        String nom = "ADELI";

        if (reunionRepository.existsByNom(nom)) {
            System.out.println("Fail -> Name is already taken!");
          } else{
                       
            Reunion reunion = new Reunion();
        reunion.setNom(nom);
        reunion.setFondateur("Sévérin CHIHIMO");
        reunion.setPays("Belgique");
        reunion.setTel(494913093);
        reunion.setDate_creation(LocalDate.now());
            reunionRepository.save(reunion);
            System.out.println("Réunion Créée");
        }
    }
    
}
