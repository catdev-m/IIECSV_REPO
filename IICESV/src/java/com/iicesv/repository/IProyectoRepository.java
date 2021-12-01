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
public interface IProyectoRepository extends JpaRepository<IiceProyecto, Integer> {

    @Query(nativeQuery = true, value = "SELECT ifnull(max(id_proyecto)+1,1) FROM iice_proyecto")
    public int nextId();

    @Query(nativeQuery = true, value = "SELECT * FROM iice_proyecto where estado ='A' ")
    public List<IiceProyecto> cargarProyectos();

    @Query(nativeQuery = true, value = "SELECT id_fase, fase FROM iicesv_db.iiec_fases where estado  = 'Y' ")
    public List<Object[]> cargarFases();

    @Query(nativeQuery = true, value = "SELECT id_actividad , nombre_actividad  FROM iicesv_db.iiec_activity where estado = 'A' and id_fase = ?1")
    public List<Object[]> cargarActividadesByIdFase(int idFase);

    @Query(nativeQuery = true, value = "SELECT id , nombre FROM iicesv_db.iice_resource_catalog")
    public List<Object[]> obtenerRecursos();

    @Query(nativeQuery = true, value = "SELECT b.id , b.id_actividad , c.nombre_actividad  , a.id_proyecto ,  a.id_fase , DATE_FORMAT(b.fecha_creacion, \"%m/%d/%Y\") fecha FROM iicesv_db.iice_proyecto a , \n"
            + "iice_proyecto_actividades b , iiec_activity c\n"
            + "where a.id_proyecto=b.id_proyecto\n"
            + "and a.id_fase=b.id_fase\n"
            + "and c.id_fase=b.id_fase\n"
            + "and b.id_actividad=c.id_actividad and  a.id_proyecto=?1 and a.id_fase = ?2")
    public List<Object[]> obtenerActividadesPorProyectoyFase(int idProyecto, int idFase);

    @Query(nativeQuery = true, value = "SELECT c.id , c.id_recurso , d.id_actividad , a.id_proyecto , e.nombre , DATE_FORMAT(c.fecha_creacion, \"%m/%d/%Y\") fecha \n"
            + "FROM iicesv_db.iice_proyecto a ,  iiec_fases b , iice_proyecto_recurso c , iiec_activity d , iice_resource_catalog e\n"
            + "where  a.id_fase=b.id_fase\n"
            + "and c.id_proyecto=a.id_proyecto\n"
            + "and c.id_actividad=d.id_actividad\n"
            + "and d.id_fase=b.id_fase\n"
            + "and e.id=c.id_recurso\n"
            + "and a.id_proyecto = ?1 and d.id_actividad=?2")
    public List<Object[]> obtenerRecursosPorProyectoyActividad(int idProyecto, int idActividad);

    @Query(nativeQuery = true, value = "SELECT fase FROM iicesv_db.iiec_fases where id_fase  = ?1 ")
    public String obtenerNombreFase(int idfase);

    @Query(nativeQuery = true, value = "SELECT nombre_actividad FROM iicesv_db.iiec_activity where id_actividad  = ?1 ")
    public String obtenerNombreActividad(int idActividad);

}
