/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Elections;
import com.example.demo.entity.Session;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casimir
 */
@Repository
public interface ElectionRepository extends JpaRepository<Elections, Long> {
    
    Elections findByUserAndSession(String user, Session sess);
    
    String bureau = "SELECT e.id_election, e.fonction, e.montant, e.user, s.debut as session, e.tel\n" +
        "from elections e\n" +
        "join session s on s.id_session = e.id_session \n" +
        "where s.etat = 1 \n"+
        "order by e.user asc";
  
    @Query(value=bureau, nativeQuery = true)
    public List<JSONObject> Bureau();
    
    Boolean existsByUserAndSession(String user, Session s);
    
    String evolution = "SELECT count(distinct e.user)as nbre, s.debut as session "
            + "FROM elections e join session s on s.id_session = e.id_session \n "
            + "GROUP by s.debut order by s.debut asc";
  
    @Query(value=evolution, nativeQuery = true)
    public List<JSONObject> evolution();
    
    String evolution2 = "SELECT count(distinct e.user)as nbre, s.debut as session, s.montant "
            + "FROM session s \n "
            + "join elections e on e.id_session = s.id_session "
            + "GROUP by s.debut order by s.debut asc";
  
    @Query(value=evolution2, nativeQuery = true)
    public List<JSONObject> evolution2();
}