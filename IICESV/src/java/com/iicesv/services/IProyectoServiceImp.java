/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.dto.ListaProyectosDto;
import com.iicesv.entities.IiceProyecto;
import com.iicesv.repository.IProyectoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */
@Service
public class IProyectoServiceImp implements IProyectoService, Serializable {

    @Autowired
    IProyectoRepository iProyectoRepository;

    @Override
    public IiceProyecto guardar(IiceProyecto bk) {
        return iProyectoRepository.save(bk);
    }

    @Override
    public IiceProyecto actualizar(IiceProyecto bk) {
        return iProyectoRepository.save(bk);
    }

    @Override
    public void eliminar(IiceProyecto bk) {
        iProyectoRepository.delete(bk);
    }

    @Override
    public List<IiceProyecto> obtenerProyectos() {
        return iProyectoRepository.findAll();
    }

    @Override
    public IiceProyecto findByIdProyecto(int pk) {
        return iProyectoRepository.findOne(pk);
    }

    @Override
    public int nextId() {
        return iProyectoRepository.nextId();
    }

    @Override
    public List<ListaProyectosDto> cargarProyectos() {
        List<ListaProyectosDto> listado = new ArrayList<>();
        for (IiceProyecto i : iProyectoRepository.cargarProyectos()) {
            ListaProyectosDto lista2 = new ListaProyectosDto();
            lista2.setDescripcion(i.getNombreProyecto());
            lista2.setId_proy(i.getIdProyecto());
            listado.add(lista2);
        }
        return listado;

    }

    @Override
    public List<Object[]> cargarFases() {
        return iProyectoRepository.cargarFases();
    }

    @Override
    public List<Object[]> cargarActividadesByIdFase(int idFase) {
        return iProyectoRepository.cargarActividadesByIdFase(idFase);
    }

    @Override
    public List<Object[]> obtenerRecursos() {
        return iProyectoRepository.obtenerRecursos();
    }

    @Override
    public String obtenerNombreFase(int idfase) {
        return iProyectoRepository.obtenerNombreFase(idfase);
    }

    @Override
    public String obtenerNombreActividad(int idActividad) {
        return iProyectoRepository.obtenerNombreActividad(idActividad);
    }

}
