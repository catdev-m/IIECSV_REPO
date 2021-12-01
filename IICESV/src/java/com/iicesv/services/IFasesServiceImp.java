/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiecFases;
import com.iicesv.repository.IFasesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */

@Service
public class IFasesServiceImp implements IFasesServices{

    @Autowired
    IFasesRepository iFasesRepository;
    
    @Override
    public List<IiecFases> obtenerFases() {
        return iFasesRepository.findAll();
    }

    @Override
    public IiecFases obtenerFase(int idFase) {
       return iFasesRepository.findOne(idFase);
    }

    
}
