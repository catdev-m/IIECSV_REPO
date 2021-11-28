/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceEjecucionFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Irvin
 */
@Repository
public interface iFinanzasRepository extends JpaRepository<IiceEjecucionFinanciera,Integer> {
    
    @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_ejecucion_financiera")
    public int nextId();
    
}
