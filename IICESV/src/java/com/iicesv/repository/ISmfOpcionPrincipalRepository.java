/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceOpcionPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author irvin_monterroza
 */
public interface ISmfOpcionPrincipalRepository extends JpaRepository<IiceOpcionPrincipal, Integer>{
    
     @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_opcion_principal")
     public int nextId();
    
}
