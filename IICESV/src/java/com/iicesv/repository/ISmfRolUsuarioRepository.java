/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceRolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Denisse
 */
@Repository
public interface ISmfRolUsuarioRepository extends JpaRepository<IiceRolUsuario, Integer>{
    
     @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1) FROM iice_rol_usuario")
     public int nextId();
     
     @Query(nativeQuery = true, value = "SELECT * FROM iice_rol_usuario where id_user = ?")
     public IiceRolUsuario obtenerUsuarioROl(int idUser);

    
}
