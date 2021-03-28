/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Amande;
import com.example.demo.entity.Prets;
import com.example.demo.entity.Reunion;
import com.example.demo.entity.Session;
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
public interface PretRepository extends JpaRepository<Prets, Long> {
    List<Prets> findByDatePret(LocalDate date);
    List<Prets> findByRembourse(boolean etat);
    List<Prets> findByUser(User user);
}