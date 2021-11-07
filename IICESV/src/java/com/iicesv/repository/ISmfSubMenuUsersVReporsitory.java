/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.repository;

import com.iicesv.entities.IiceSubMenuUsersV;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface ISmfSubMenuUsersVReporsitory extends JpaRepository<IiceSubMenuUsersV, Integer>{
    
    
    @Query(nativeQuery = true , value = "SELECT * FROM iice_sub_menu_users_v where id_opc_ppal= ?1 and usr = ?2 and id_rol= ?3")
    public List<IiceSubMenuUsersV> obtenerSubMenu(int idOpcPpal , String usr , int idRol);
    
}
