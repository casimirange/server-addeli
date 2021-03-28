/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Retenue;
import com.example.demo.entity.Reunion;
import com.example.demo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casimir
 */
@Repository
public interface RetenueRepository extends JpaRepository<Retenue, Long> {
    Retenue findFirstByOrderByIdRetenueDesc();
}