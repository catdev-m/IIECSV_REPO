/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceOpcionRol;
import com.iicesv.repository.ISmfOpcionRolRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */
@Service
public class SmfOpcionRolServicesImpl implements ISmfOpcionRolServices,Serializable{

    
    @Autowired
    ISmfOpcionRolRepository iSmfOpcionRolRepository;
    
    @Override
    public void guardarOpcionRole(IiceOpcionRol opc) {
        iSmfOpcionRolRepository.save(opc);
    }

    @Override
    public void eliminarOpcionRole(IiceOpcionRol opc) {
        iSmfOpcionRolRepository.delete(opc);
    }

    @Override
    public void actualizarOpcionRole(IiceOpcionRol opc) {
        iSmfOpcionRolRepository.save(opc);
    }

    @Override
    public void eliminarOpcionesPorRol(int idRol) {
        iSmfOpcionRolRepository.eliminarOpcionesPorRol(idRol);
    }
    
}
