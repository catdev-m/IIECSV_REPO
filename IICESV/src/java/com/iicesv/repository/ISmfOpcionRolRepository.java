/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceOpcionRol;
import com.iicesv.entities.IiceOpcionRolPK;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Denisse
 */
@Repository
public interface ISmfOpcionRolRepository extends JpaRepository<IiceOpcionRol, IiceOpcionRolPK>{
    
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query(nativeQuery = true , value = "delete from iice_opcion_rol where id_rol = ? ")
    public void eliminarOpcionesPorRol(int idRol);
    
}
