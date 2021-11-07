/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceOpcionPrincipal;
import com.iicesv.repository.ISmfOpcionPrincipalRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */

@Service
public class SmfOpcionPrincipalServicesImpl implements ISmfOpcionPrincipalServices,Serializable{

    
    
    @Autowired
    ISmfOpcionPrincipalRepository iSmfOpcionPrincipalRepository;
    
    @Override
    public IiceOpcionPrincipal guardar(IiceOpcionPrincipal bk) {
        return iSmfOpcionPrincipalRepository.save(bk);
    }

    @Override
    public IiceOpcionPrincipal actualizar(IiceOpcionPrincipal bk) {
        return iSmfOpcionPrincipalRepository.save(bk);
    }

    @Override
    public void eliminar(IiceOpcionPrincipal bk) {
         iSmfOpcionPrincipalRepository.delete(bk);
    }

    @Override
    public List<IiceOpcionPrincipal> obtenerOpcionPrincpal() {
        return iSmfOpcionPrincipalRepository.findAll();
    }

    @Override
    public int nextId() {
        return iSmfOpcionPrincipalRepository.nextId();
    }

    @Override
    public IiceOpcionPrincipal findByIdOpcP(int pk) {
        return iSmfOpcionPrincipalRepository.findOne(pk);
    }
    
}
