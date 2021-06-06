/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author Casimir
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        }),
        @UniqueConstraint(columnNames = {
            "tel"
        })
})
//@Data //pour les getter & setter
//@NoArgsConstructor //constructeur sans paramètres
//@AllArgsConstructor //constructeur avec paramètres
public class User{
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Size(min=3, max = 50)
    private String name;
 
    @NotBlank
    @Size(min=3, max = 50)
    private String username;
 
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
 
    @NotBlank
    @Size(min=6, max = 100)
    private String password;
    
    @NotBlank
    @Size(min=9, max = 13)
    private String tel; 
    
    @Column(columnDefinition = "BIT default true", length = 1)
    private boolean etat;
 
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Amande> amandes;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Tontine> tontines;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Planing> planings;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Prets> pret;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Retenue> retenue;

    public User() {
    }

    public User(String name, String username, String email, String password, String tel, boolean etat) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
    
    

}
