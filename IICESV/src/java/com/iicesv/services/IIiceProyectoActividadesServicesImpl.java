/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceProyectoActividades;
import com.iicesv.repository.IiceProyectoActividadesRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */

@Service
public class IIiceProyectoActividadesServicesImpl implements Serializable , IIiceProyectoActividadesServices{
    
    
    @Autowired
    IiceProyectoActividadesRepository iiceProyectoActividadesRepository;

    @Override
    public void guardar(IiceProyectoActividades bk) {
        iiceProyectoActividadesRepository.save(bk);
    }

    @Override
    public void actualizar(IiceProyectoActividades bk) {
        iiceProyectoActividadesRepository.save(bk);
    }

    @Override
    public void eliminar(IiceProyectoActividades bk) {
        iiceProyectoActividadesRepository.delete(bk);
    }

    @Override
    public List<IiceProyectoActividades> obtenerActividadPorIdProyecto(int idProyecto) {
       return iiceProyectoActividadesRepository.obtenerActividadPorIdProyecto(idProyecto);
    }

    @Override
    public IiceProyectoActividades findByIdActividad(int pk) {
        return iiceProyectoActividadesRepository.findOne(pk);
    }

    @Override
    public int nextId() {
        return iiceProyectoActividadesRepository.nextId();
    }
    
}
