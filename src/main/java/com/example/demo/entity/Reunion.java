/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Casimir
 */
@Entity
@Table(name = "reunion")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReunion;
 
    @NotBlank
    private String nom;
 
    @NotBlank
    private LocalDate creation;
 
    @NotBlank
    private String fondateur;
 
    @NotBlank
    private String pays;
    
    @NotBlank
    @Size(min=9, max = 13)
    private Long tel;
    
    @OneToMany(mappedBy = "reunion", cascade = CascadeType.ALL)
    public List<Session> session;

    public Reunion() {
    }

    public Reunion(String nom, LocalDate creation, String fondateur, String pays) {
        this.nom = nom;
        this.creation = creation;
        this.fondateur = fondateur;
        this.pays = pays;
    }

    public Reunion(String nom, LocalDate creation, String fondateur, String pays, Long tel) {
        this.nom = nom;
        this.creation = creation;
        this.fondateur = fondateur;
        this.pays = pays;
        this.tel = tel;
    }

    public Long getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(Long id) {
        this.idReunion = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate_creation() {
        return creation;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public void setDate_creation(LocalDate creation) {
        this.creation = creation;
    }

    public String getFondateur() {
        return fondateur;
    }

    public void setFondateur(String fondateur) {
        this.fondateur = fondateur;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
    
}
