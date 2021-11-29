/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */

@Repository
public interface IArchivoRepository extends JpaRepository<IiceArchivo, Integer>{
     @Query(nativeQuery = true, value = "SELECT ifnull(max(id_archivo)+1,1) FROM iice_archivo")
     public int nextId();
}
