/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;
import com.iicesv.entities.IiceInspeccionProyectos;
import com.iicesv.repository.IInspeccionRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Chavez
 */
@Service
public class SInspeccionServicesImpl implements IInspeccionServices, Serializable {

    @Autowired
    IInspeccionRepository iInspeccionRepository;

    @Override
    public IiceInspeccionProyectos guardar(IiceInspeccionProyectos bk) {
        return iInspeccionRepository.save(bk);
    }

    @Override
    public IiceInspeccionProyectos actualizar(IiceInspeccionProyectos bk) {
        return iInspeccionRepository.save(bk);
    }

    @Override
    public void eliminar(IiceInspeccionProyectos bk) {
        iInspeccionRepository.delete(bk);
    }

    @Override
    public List<IiceInspeccionProyectos> obtenerInspeccion() {
        return iInspeccionRepository.findAll();
    }

    @Override
    public int nextId() {
        return iInspeccionRepository.nextId();
    }

    @Override
    public IiceInspeccionProyectos findByIdInspec(int pk) {
        return iInspeccionRepository.findOne(pk);
    }
}
