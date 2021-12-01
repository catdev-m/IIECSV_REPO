/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceProyectoActividades;
import java.util.List;

/**
 *
 * @author Denisse
 */
public interface IIiceProyectoActividadesServices {
    public void guardar(IiceProyectoActividades bk);
    public void actualizar(IiceProyectoActividades bk);
    public void eliminar(IiceProyectoActividades bk);
    public List<IiceProyectoActividades> obtenerActividadPorIdProyecto(int idProyecto);
    public IiceProyectoActividades findByIdActividad(int pk);
    public int nextId();
}
