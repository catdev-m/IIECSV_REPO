/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceProyectoRecurso;
import com.iicesv.repository.IiceProyectoRecursoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */
@Service
public class IIiceProyectoRecursoServicesImpl implements Serializable , IIiceProyectoRecursoServices{
    
    @Autowired
    IiceProyectoRecursoRepository   iiceProyectoRecursoRepository;

    @Override
    public void guardar(IiceProyectoRecurso bk) {
        iiceProyectoRecursoRepository.save(bk);
    }

    @Override
    public void actualizar(IiceProyectoRecurso bk) {
        iiceProyectoRecursoRepository.save(bk);
    }

    @Override
    public void eliminar(IiceProyectoRecurso bk) {
        iiceProyectoRecursoRepository.delete(bk);
    }

    @Override
    public List<IiceProyectoRecurso> obtenerRecursosPorIdProyecto(int idProyecto) {
        return iiceProyectoRecursoRepository.obtenerRecursosPorIdProyecto(idProyecto);
    }

    @Override
    public IiceProyectoRecurso findByIdRecurso(int pk) {
         return iiceProyectoRecursoRepository.findOne(pk);
    }

    @Override
    public int nextId() {
        return iiceProyectoRecursoRepository.nextId();
    }
    
}
