/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;
import com.iicesv.entities.IiceActivityCatalog;
import java.util.List;
/**
 *
 * @author User
 */
public interface IActivityServices {
    public IiceActivityCatalog guardar(IiceActivityCatalog bk);
    public IiceActivityCatalog actualizar(IiceActivityCatalog bk);
    public void eliminar(IiceActivityCatalog bk);
    public List<IiceActivityCatalog> obtenerActivityCatalogs();
    public IiceActivityCatalog findByIdCatR(int pk);
    public int nextId();
}
