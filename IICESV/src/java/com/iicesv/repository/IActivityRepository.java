/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;
import com.iicesv.entities.IiceActivityCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author User
 */
public interface IActivityRepository extends JpaRepository<IiceActivityCatalog, Integer>{
    @Query(nativeQuery = true, value = "SELECT ifnull(max(id_actividad)+1,1) FROM iiec_activity")
     public int nextId();
}
