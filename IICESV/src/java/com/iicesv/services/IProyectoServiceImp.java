/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.dto.ListaProyectosDto;
import com.iicesv.dto.ListadorecursosPorActividadDTO;
import com.iicesv.dto.ProyectoActividadDTO;
import com.iicesv.entities.IiceProyecto;
import com.iicesv.repository.IProyectoRepository;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Override
    public List<ProyectoActividadDTO> obtenerActividadesPorProyectoyFase(int idProyecto, int idFase) {
        
        List<ProyectoActividadDTO> lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         try {
        for(Object[] obj : iProyectoRepository.obtenerActividadesPorProyectoyFase(idProyecto, idFase)){
            ProyectoActividadDTO data = new ProyectoActividadDTO();
            data.setId(Integer.parseInt(obj[0].toString()));
            data.setIdActividad(Integer.parseInt(obj[1].toString()));
            data.setNombreActividad((obj[2].toString()));
            data.setIdProyecto(Integer.parseInt(obj[3].toString()));
            data.setIdFase(Integer.parseInt(obj[4].toString()));
            data.setFechaCreacion(sdf.parse(obj[5].toString()));
           
            lista.add(data);
        
        }
         } catch (ParseException ex) {
                Logger.getLogger(IProyectoServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return lista;
        
    }

    @Override
    public List<ListadorecursosPorActividadDTO> obtenerRecursosPorProyectoyActividad(int idProyecto, int idActividad) {
        List<ListadorecursosPorActividadDTO> lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         try {
        for(Object[] obj : iProyectoRepository.obtenerRecursosPorProyectoyActividad(idProyecto, idActividad)){
            ListadorecursosPorActividadDTO data = new ListadorecursosPorActividadDTO();
            data.setId(Integer.parseInt(obj[0].toString()));
            data.setIdRecurso(Integer.parseInt(obj[1].toString()));
            data.setIdActividad(Integer.parseInt(obj[2].toString()));
            data.setIdProyecto(Integer.parseInt(obj[3].toString()));
            data.setDescripcionRecurso((obj[4].toString()));
            data.setFechaCreacion(sdf.parse(obj[5].toString()));
           
            lista.add(data);
        
        }
         } catch (ParseException ex) {
                Logger.getLogger(IProyectoServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return lista;
    }

}
