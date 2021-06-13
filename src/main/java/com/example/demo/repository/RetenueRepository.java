/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Retenue;
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
public interface RetenueRepository extends JpaRepository<Retenue, Long> {
    Retenue findFirstByOrderByIdRetenueDesc();
    
    String allMangwa = "select r.id_retenue, r.date, r.motif, r.credit, r.debit,"
            + "r.solde, u.name as nom from retenue r "
            + "join user u on u.id = r.id_user "
            + "ORDER by r.id_retenue desc ";
  
    @Query(value=allMangwa, nativeQuery = true)
    public List<JSONObject> findMangwa();
}