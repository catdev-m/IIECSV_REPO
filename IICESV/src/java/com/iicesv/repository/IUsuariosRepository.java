/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface IUsuariosRepository extends JpaRepository<IiceUsuarios, Integer> {

    @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_usuarios")
    public int nextId();

    @Query(value = "select * from  iice_usuarios  where usr = ? and estado='A' ", nativeQuery = true)
    public IiceUsuarios findByUsr(String usr);
    
    
    @Query(value = "select * from  iice_usuarios  where estado='A' ", nativeQuery = true)
    public List<IiceUsuarios> obtenerUsuariosActivos();

}
