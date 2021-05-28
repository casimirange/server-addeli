/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Communique;
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
public interface CommuniqueRepository extends JpaRepository<Communique, Long> {
    String cr = "select c.id_communique, c.date, c.details from communique c "
            + "join session s on c.id_session = s.id_session "
            + "where s.etat = 1"
            + " ORDER by c.date desc ";
  
    @Query(value=cr, nativeQuery = true)
    public List<JSONObject> findCommunique();
}