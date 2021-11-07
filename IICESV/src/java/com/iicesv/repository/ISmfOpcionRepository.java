/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceOpcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Denisse
 */
public interface ISmfOpcionRepository extends JpaRepository<IiceOpcion, Integer>{
    
     @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_opcion")
     public int nextId();
     
     @Query(nativeQuery = true , value = "select  *  from iice_opcion  where id  in (select  id_opcion  from  iice_opcion_rol where id_rol=?)")
     public List<IiceOpcion> opcionesAsignadasPorRol(int idRol);
     
     @Query(nativeQuery = true , value = "select  *  from iice_opcion  where id not in (select  id_opcion  from  iice_opcion_rol where id_rol=?)")
     public List<IiceOpcion> opcionesDisponibles(int idRol);
}
