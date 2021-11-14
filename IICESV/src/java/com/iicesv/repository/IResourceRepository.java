/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceResourceCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IResourceRepository extends JpaRepository<IiceResourceCatalog, Integer>{
    
     @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_resource_catalog")
     public int nextId();
    
}
