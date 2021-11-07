/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceOpcion;
import java.util.List;

/**
 *
 * @author Denisse
 */
public interface ISmfOpcionServices {
    public IiceOpcion guardar(IiceOpcion bk);
    public IiceOpcion actualizar(IiceOpcion bk);
    public void eliminar(IiceOpcion bk);
    public List<IiceOpcion> obtenerOpciones();
    public int nextId();
    public List<IiceOpcion> opcionesAsignadasPorRol(int idRol);
    public List<IiceOpcion> opcionesDisponibles(int idRol);
}
