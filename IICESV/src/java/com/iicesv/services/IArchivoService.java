/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceArchivo;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface IArchivoService {

    public List<IiceArchivo> obtenerArchivos();

    public void guardarArchivo(IiceArchivo opc);

    public void eliminarArchivo(IiceArchivo opc);

    public void actualizarArchivo(IiceArchivo opc);

     public int nextId();
}
