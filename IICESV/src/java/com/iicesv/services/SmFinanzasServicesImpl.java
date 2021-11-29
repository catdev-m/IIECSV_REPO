/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceEjecucionFinanciera;
import com.iicesv.repository.iFinanzasRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */

@Service
public class SmFinanzasServicesImpl implements IFinanzasServices , Serializable{
    @Autowired
    iFinanzasRepository iFinanzasRepository;

    @Override
    public IiceEjecucionFinanciera guardar(IiceEjecucionFinanciera bk) {
        return iFinanzasRepository.save(bk); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IiceEjecucionFinanciera actualizar(IiceEjecucionFinanciera bk) {
        return iFinanzasRepository.save(bk); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(IiceEjecucionFinanciera bk) {
         iFinanzasRepository.delete(bk); 
    }

    @Override
    public List<IiceEjecucionFinanciera> obtenerEjecucionFinanciera() {
        return iFinanzasRepository.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public int nextId() {
        return iFinanzasRepository.nextId();
    }

    @Override
    public IiceEjecucionFinanciera findByIdCatR(int pk) {
        return iFinanzasRepository.findOne(pk);
    }
    
     
}


