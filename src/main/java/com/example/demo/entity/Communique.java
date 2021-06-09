/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Casimir
 */
@Entity
public class Communique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommunique;
 
//    @NotBlank
    private LocalDate date;
 
//    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String details;
    
    @ManyToOne(fetch = FetchType.EAGER) //plusieurs lignes pour un département
	@JoinColumn(name = "idSession")
    public Session session;

    public Communique() {
    }

    public Communique(LocalDate date, String details, Session session) {
        this.date = date;
        this.details = details;
        this.session = session;
    }

    public Long getIdCommunique() {
        return idCommunique;
    }

    public void setIdCommunique(Long idCommunique) {
        this.idCommunique = idCommunique;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
}
