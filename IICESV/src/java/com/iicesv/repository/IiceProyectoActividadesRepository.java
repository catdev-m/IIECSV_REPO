/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceProyectoActividades;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Denisse
 */

@Repository
public interface IiceProyectoActividadesRepository extends JpaRepository<IiceProyectoActividades, Integer> {
    @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_proyecto_actividades")
    public int nextId();
    
    
     @Query(nativeQuery = true, value = "SELECT * FROM iice_proyecto_actividades where id_proyecto= ? ")
     public List<IiceProyectoActividades> obtenerActividadPorIdProyecto(int idProyecto);
}
