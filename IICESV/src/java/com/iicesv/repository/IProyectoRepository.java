/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Denisse
 */

@Repository
public interface IProyectoRepository extends JpaRepository<IiceProyecto, Integer>{
    @Query(nativeQuery = true, value = "SELECT ifnull(max(id_proyecto)+1,1) FROM iice_proyectos")
     public int nextId();
     
     @Query(nativeQuery = true, value = "SELECT * FROM iice_proyecto where estado ='A' ")
     public List<IiceProyecto> cargarProyectos();
     
      @Query(nativeQuery = true, value = "SELECT id_fase, fase FROM iicesv_db.iiec_fases where estado  = 'Y' ")
     public List<Object[]> cargarFases();
     
     @Query(nativeQuery = true, value = "SELECT id_actividad , nombre_actividad  FROM iicesv_db.iiec_activity where estado = 'A' and id_fase = ?1")
     public List<Object[]> cargarActividadesByIdFase(int idFase);
     
     @Query(nativeQuery = true, value = "SELECT id , nombre FROM iicesv_db.iice_resource_catalog")
     public List<Object[]> obtenerRecursos();
     
     
     
     @Query(nativeQuery = true, value = "SELECT fase FROM iicesv_db.iiec_fases where id_fase  = ?1 ") 
    public String obtenerNombreFase(int idfase);
    
    @Query(nativeQuery = true, value = "SELECT nombre_actividad FROM iicesv_db.iiec_activity where id_actividad  = ?1 ") 
    public String obtenerNombreActividad(int idActividad);
    
     
     
     
}
