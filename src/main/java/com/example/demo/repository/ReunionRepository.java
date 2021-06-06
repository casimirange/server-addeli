/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Reunion;
import com.example.demo.entity.User;
import java.util.List;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casimir
 */
@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {
    Optional<Reunion> findByNom(String nom);
    Boolean existsByNom(String nom);
    
    String reunion = "SELECT r.creation as date, r.fondateur FROM reunion r WHERE r.id_reunion = 1";
  
    @Query(value=reunion, nativeQuery = true)
    public JSONObject getReunion();
}