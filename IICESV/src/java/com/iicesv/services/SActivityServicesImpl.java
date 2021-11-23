/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;
import com.iicesv.entities.IiceActivityCatalog;
import com.iicesv.repository.IActivityRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author User
 */
@Service
public class SActivityServicesImpl implements IActivityServices, Serializable{
    
    @Autowired
    IActivityRepository iActivityRepository;
    
    
    @Override
    public IiceActivityCatalog guardar(IiceActivityCatalog bk) {
        return iActivityRepository.save(bk);
    }

    @Override
    public IiceActivityCatalog actualizar(IiceActivityCatalog bk) {
        return iActivityRepository.save(bk);
    }

    @Override
    public void eliminar(IiceActivityCatalog bk) {
         iActivityRepository.delete(bk);
    }

    @Override
    public List<IiceActivityCatalog> obtenerActivityCatalogs() {
        return iActivityRepository.findAll();
    }

    @Override
    public int nextId() {
        return iActivityRepository.nextId();
    }

    @Override
    public IiceActivityCatalog findByIdCatR(int pk) {
        return iActivityRepository.findOne(pk);
    }
}
