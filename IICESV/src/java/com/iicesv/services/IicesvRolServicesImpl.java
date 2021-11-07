/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;


import com.iicesv.entities.IiceRol;
import com.iicesv.repository.IIicesvRolRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin_monterroza
 */
@Service
public class IicesvRolServicesImpl implements IIicesvRolServices,Serializable{

    
    @Autowired
    IIicesvRolRepository iSmfRolRepository;
    
    @Override
    public List<IiceRol> obtenerRoles() {
        return iSmfRolRepository.findAll();
    }

    @Override
    public IiceRol obtenerRolById(int idRol) {
        return iSmfRolRepository.findOne(idRol);
    }
    
}
