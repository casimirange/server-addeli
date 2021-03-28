/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Planing;
import com.example.demo.entity.Session;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casimir
 */
@Repository
public interface PlanningRepository extends JpaRepository<Planing, Long> {
    Boolean existsByDate(LocalDate date);
    Boolean existsByDateAndEtat(LocalDate date, Boolean b);
    Long countBySession(Session session);
}