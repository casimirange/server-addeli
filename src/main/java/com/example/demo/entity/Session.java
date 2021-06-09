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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author Casimir
 */
@Entity
@Table(name = "session")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;
 
    @NotBlank
    private int duree;
 
    @NotBlank
    private int fonds;
 
    @NotBlank
    private double montant;
 
    @NotBlank
    private double retenue;
 
    @NotBlank
    private LocalDate debut;
 
    @NotBlank
    private LocalDate fin;
 
    @NotBlank
    private boolean etat;
 
    @NotBlank
    private boolean encours;
 
    
    private double taux;
 
    @NotBlank
    private int participants;
    
    @ManyToOne(fetch = FetchType.EAGER) //plusieurs lignes pour un département
	@JoinColumn(name = "idReunion")
    @Ignore
    public Reunion reunion;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Tontine> tontines;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Evenement> evenements;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Beneficiaire> beneficiaires;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Prets> prets;    
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Amande> amandes;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Discipline> discipline;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<Elections> elections;
    
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    public List<CompteRendu> compteRendu;


    public Session() {
    }   

    public Session(int duree, int fonds, double montant, double retenue, LocalDate debut, LocalDate fin, boolean etat, boolean encours, double taux, int participants, Reunion reunion) {
        this.duree = duree;
        this.fonds = fonds;
        this.montant = montant;
        this.retenue = retenue;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
        this.encours = encours;
        this.taux = taux;
        this.participants = participants;
        this.reunion = reunion;
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long id) {
        this.idSession = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getFonds() {
        return fonds;
    }

    public void setFonds(int fonds) {
        this.fonds = fonds;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public boolean isEncours() {
        return encours;
    }

    public void setEncours(boolean encours) {
        this.encours = encours;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public List<Tontine> getTontines() {
        return tontines;
    }

    public void setTontines(List<Tontine> tontines) {
        this.tontines = tontines;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public List<Beneficiaire> getBeneficiaires() {
        return beneficiaires;
    }

    public void setBeneficiaires(List<Beneficiaire> beneficiaires) {
        this.beneficiaires = beneficiaires;
    }

    public double getRetenue() {
        return retenue;
    }

    public void setRetenue(double retenue) {
        this.retenue = retenue;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
