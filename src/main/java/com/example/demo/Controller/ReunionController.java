/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;


//import com.example.demo.entity.Role;
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
import com.example.demo.repository.ReunionRepository;
import com.example.demo.repository.UserRepository;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UtilisateurRepository;
//import com.example.demo.security.jwt.JwtProvider;

//import com.example.demo.util.RoleEnum;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/reunion")
public class ReunionController {
    
    @Autowired
    ReunionRepository reunionRepository;

    @PostMapping("/new")
    public ResponseEntity<?> createReunion(@Valid @RequestBody Reunion reunion) {     
        reunionRepository.save(reunion);
      return ResponseEntity.ok(new Reunion(reunion.getNom(), reunion.getDate_creation(), reunion.getFondateur(), reunion.getPays(), reunion.getTel()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReunion(@Valid @RequestBody Reunion reunion) {
        
      reunionRepository.save(reunion);
      return ResponseEntity.ok(new Reunion(reunion.getNom(), reunion.getDate_creation(), reunion.getFondateur(), reunion.getPays(), reunion.getTel()));
    }

    @GetMapping
    public Reunion getReunion(){      
        return reunionRepository.findById(Long.parseLong("1")).get();
    }

    @DeleteMapping("/{id}")
    public void deleteReunion(@PathVariable Long id) {
            reunionRepository.delete(reunionRepository.findById(id).get());
    }
        
}
