/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

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
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByName(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
//    Long count(User u);
    
    String count = "select count(u.id) from user u ";
  
    @Query(value=count, nativeQuery = true)
    public Long Count();
}