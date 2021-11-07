/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceSubMenuUsersV;
import java.util.List;

/**
 *
 * @author irvin_monterroza
 */
public interface ISmfSubMenuUsersVServices {
    
    public List<IiceSubMenuUsersV> obtenerSubMenu(int idOpcPpal , String usr , int idRol);
}
