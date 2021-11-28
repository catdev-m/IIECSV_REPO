/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;
import com.iicesv.entities.IiceInspeccionProyectos;
import java.util.List;
/**
 *
 * @author Jonathan Chavez
 */
public interface IInspeccionServices {
    public IiceInspeccionProyectos guardar(IiceInspeccionProyectos bk);
    public IiceInspeccionProyectos actualizar(IiceInspeccionProyectos bk);
    public void eliminar(IiceInspeccionProyectos bk);
    public List<IiceInspeccionProyectos> obtenerInspeccion();
    public IiceInspeccionProyectos findByIdInspec(int pk);
    public int nextId();
}
