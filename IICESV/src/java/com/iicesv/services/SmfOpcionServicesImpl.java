/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceOpcion;
import com.iicesv.repository.ISmfOpcionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin_monterroza
 */
@Service
public class SmfOpcionServicesImpl implements ISmfOpcionServices{

    
    @Autowired
    ISmfOpcionRepository iSmfOpcionRepository;
    @Override
    public IiceOpcion guardar(IiceOpcion bk) {
        return iSmfOpcionRepository.save(bk);
    }

    @Override
    public IiceOpcion actualizar(IiceOpcion bk) {
        return iSmfOpcionRepository.save(bk);
    }

    @Override
    public void eliminar(IiceOpcion bk) {
         iSmfOpcionRepository.delete(bk);
    }

    @Override
    public List<IiceOpcion> obtenerOpciones() {
        return iSmfOpcionRepository.findAll();
    }

    @Override
    public int nextId() {
       return iSmfOpcionRepository.nextId();
    }

    @Override
    public List<IiceOpcion> opcionesAsignadasPorRol(int idRol) {
        return iSmfOpcionRepository.opcionesAsignadasPorRol(idRol);
    }

    @Override
    public List<IiceOpcion> opcionesDisponibles(int idRol) {
        return iSmfOpcionRepository.opcionesDisponibles (idRol);
    }
    
}
