/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiecFases;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Denisse
 */
public interface IFasesRepository extends JpaRepository<IiecFases, Integer>{
    
}
