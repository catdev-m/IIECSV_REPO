/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceRolUsuario;
import com.iicesv.repository.ISmfRolUsuarioRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin_monterroza
 */
@Service
public class SmfRolUsuarioServicesImpl implements ISmfRolUsuarioServices,Serializable{

    @Autowired
    ISmfRolUsuarioRepository  iSmfRolUsuarioRepository;
    
    @Override
    public void guardarUsuarioRol(IiceRolUsuario usrRol) {
        iSmfRolUsuarioRepository.save(usrRol);
    }

    @Override
    public void eliminarUsuarioRol(IiceRolUsuario usrRol) {
        iSmfRolUsuarioRepository.delete(usrRol);
    }

    @Override
    public void actualizarUsuarioRol(IiceRolUsuario usrRol) {
        iSmfRolUsuarioRepository.save(usrRol);
    }

    @Override
    public int nextId() {
        return iSmfRolUsuarioRepository.nextId();
    }

    @Override
    public IiceRolUsuario obtenerUsuarioROl(int idUser) {
        return iSmfRolUsuarioRepository.obtenerUsuarioROl(idUser);
    }
    
}
