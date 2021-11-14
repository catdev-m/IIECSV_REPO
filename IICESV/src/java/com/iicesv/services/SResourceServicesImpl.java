/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;
import com.iicesv.entities.IiceResourceCatalog;
import com.iicesv.repository.IResourceRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Jonathan Chavez
 */
@Service
public class SResourceServicesImpl implements IResourceServices, Serializable{
    @Autowired
    IResourceRepository iResourceRepository;
    
    
    @Override
    public IiceResourceCatalog guardar(IiceResourceCatalog bk) {
        return iResourceRepository.save(bk);
    }

    @Override
    public IiceResourceCatalog actualizar(IiceResourceCatalog bk) {
        return iResourceRepository.save(bk);
    }

    @Override
    public void eliminar(IiceResourceCatalog bk) {
         iResourceRepository.delete(bk);
    }

    @Override
    public List<IiceResourceCatalog> obtenerResourceCatalogs() {
        return iResourceRepository.findAll();
    }

    @Override
    public int nextId() {
        return iResourceRepository.nextId();
    }

    @Override
    public IiceResourceCatalog findByIdCatR(int pk) {
        return iResourceRepository.findOne(pk);
    }
    
}
