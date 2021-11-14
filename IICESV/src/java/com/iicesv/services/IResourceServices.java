/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;
import com.iicesv.entities.IiceResourceCatalog;
import java.util.List;
/**
 *
 * @author Jonathan Chavez
 */
public interface IResourceServices {
    public IiceResourceCatalog guardar(IiceResourceCatalog bk);
    public IiceResourceCatalog actualizar(IiceResourceCatalog bk);
    public void eliminar(IiceResourceCatalog bk);
    public List<IiceResourceCatalog> obtenerResourceCatalogs();
    public IiceResourceCatalog findByIdCatR(int pk);
    public int nextId();
}
