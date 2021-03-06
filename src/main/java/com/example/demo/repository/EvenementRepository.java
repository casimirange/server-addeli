/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Beneficiaire;
import com.example.demo.entity.Evenement;
import com.example.demo.entity.Reunion;
import com.example.demo.entity.Session;
import com.example.demo.entity.Tontine;
import com.example.demo.entity.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casimir
 */
@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    List<Evenement> findByDate(LocalDate date);
    List<Evenement> findByNom(String nom);
}