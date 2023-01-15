/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Reunion;
import com.example.demo.entity.Session;
import com.example.demo.entity.User;
import java.time.LocalDate;
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
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByEtat(boolean etat);
    Session findByDebut(LocalDate date);
    Session findFirstByOrderByIdSessionDesc();
//    Session existByEtat(boolean etat);
    
    String sessionActive = "SELECT * from session s WHERE s.etat = 1";
  
    @Query(value=sessionActive, nativeQuery = true)
    public JSONObject getActiveSession();
    
    String allSession = "SELECT * from session s order by s.debut desc";
  
    @Query(value=allSession, nativeQuery = true)
    public List<JSONObject> getAllSession();
}