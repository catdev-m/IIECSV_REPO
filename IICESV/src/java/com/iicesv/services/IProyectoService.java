/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.dto.ListaProyectosDto;
import com.iicesv.entities.IiceProyecto;
import java.util.List;

/**
 *
 * @author Denisse
 */
public interface IProyectoService {
    public IiceProyecto guardar(IiceProyecto bk);
    public IiceProyecto actualizar(IiceProyecto bk);
    public void eliminar(IiceProyecto bk);
    public List<IiceProyecto> obtenerProyectos();
    public IiceProyecto findByIdProyecto(int pk);
    public int nextId();
    public List<ListaProyectosDto> cargarProyectos();
    public List<Object[]> cargarFases();
    public List<Object[]> cargarActividadesByIdFase(int idFase);
    public List<Object[]> obtenerRecursos();
    
    public String obtenerNombreFase(int idfase);
    public String obtenerNombreActividad(int idActividad);
    
}
