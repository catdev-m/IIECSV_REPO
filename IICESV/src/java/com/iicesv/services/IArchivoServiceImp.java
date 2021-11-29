/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceArchivo;
import com.iicesv.repository.IArchivoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nivrist
 */

@Service
public class IArchivoServiceImp implements IArchivoService , Serializable {

    @Autowired
    IArchivoRepository iArchivoRepository;
    
    @Override
    public List<IiceArchivo> obtenerArchivos() {
       return iArchivoRepository.findAll();
    }

    @Override
    public void guardarArchivo(IiceArchivo opc) {
        iArchivoRepository.save(opc);
    }

    @Override
    public void eliminarArchivo(IiceArchivo opc) {
        iArchivoRepository.save(opc);
    }

    @Override
    public void actualizarArchivo(IiceArchivo opc) {
        iArchivoRepository.save(opc);
    }

    @Override
    public int nextId() {
      return iArchivoRepository.nextId();
    }

   
    
}
