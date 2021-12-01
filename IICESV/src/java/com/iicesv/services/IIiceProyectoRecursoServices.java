/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceProyectoRecurso;
import java.util.List;

/**
 *
 * @author Denisse
 */
public interface IIiceProyectoRecursoServices {
    public void guardar(IiceProyectoRecurso bk);
    public void actualizar(IiceProyectoRecurso bk);
    public void eliminar(IiceProyectoRecurso bk);
    public List<IiceProyectoRecurso> obtenerRecursosPorIdProyecto(int idProyecto);
    public IiceProyectoRecurso findByIdRecurso(int pk);
    public int nextId();
}
