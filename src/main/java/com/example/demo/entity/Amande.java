/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import java.time.LocalDate;
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
public class Amande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAmande;
 
    @NotBlank
    private int montant;
 
    @NotBlank
    private LocalDate date;
 
    @NotBlank
    private String motif;
    
    @ManyToOne(fetch = FetchType.EAGER) //plusieurs lignes pour un département
	@JoinColumn(name = "idUser")
    public User user;

}
