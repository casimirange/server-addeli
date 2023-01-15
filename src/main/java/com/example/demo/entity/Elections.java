/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Casimir
 */
@Entity
public class Elections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idElection;
 
//    @NotBlank
    private String user;
    
//    @NotBlank
    private String fonction;
    
//    @NotBlank
    private int montant;
    
//    @NotBlank
    private Long tel;
    
    @ManyToOne(fetch = FetchType.EAGER) //plusieurs lignes pour un département
	@JoinColumn(name = "idSession")
    public Session session;

    public Elections() {
    }

    public Elections(String user, String fonction, int montant, Long tel, Session session) {
        this.user = user;
        this.fonction = fonction;
        this.montant = montant;
        this.tel = tel;
        this.session = session;
    }

    

    public Long getIdElection() {
        return idElection;
    }

    public void setIdElection(Long idElection) {
        this.idElection = idElection;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    
}
