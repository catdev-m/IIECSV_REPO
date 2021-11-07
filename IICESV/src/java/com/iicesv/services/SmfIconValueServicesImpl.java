/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceIconValue;
import com.iicesv.repository.ISmfIconValueRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin_monterroza
 */
@Service
public class SmfIconValueServicesImpl implements ISmfIconValueServices , Serializable{

    
    @Autowired
    ISmfIconValueRepository  iSmfIconValueRepository;
    
    @Override
    public List<IiceIconValue> obetnerIconos() {
        return iSmfIconValueRepository.findAll();
    }
    
}
