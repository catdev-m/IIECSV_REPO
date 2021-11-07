/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceMenuPrincipalUsersV;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface ISmfMenuPrincipalUsersVRepository extends JpaRepository<IiceMenuPrincipalUsersV, Integer> {
    
    @Query(nativeQuery = true ,value = "SELECT * FROM iice_menu_principal_users_v where usr = ?1  and id_rol = ?2 ")
    public List<IiceMenuPrincipalUsersV> obtenerMenuPrincipal(String usr , int idRol);
    
}
