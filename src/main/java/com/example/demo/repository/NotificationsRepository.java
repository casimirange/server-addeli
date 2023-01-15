/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Notifications;
import java.time.LocalDate;
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
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
   String all = "SELECT * FROM notifications n "
            + " where n.date between ?1 and ?2 order by n.id_notification desc";
  
    @Query(value=all, nativeQuery = true)
    public List<JSONObject> getAll(LocalDate d1, LocalDate d2);
}