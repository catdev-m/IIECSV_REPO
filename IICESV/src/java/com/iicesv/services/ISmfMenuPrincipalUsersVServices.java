/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceMenuPrincipalUsersV;
import java.util.List;

/**
 *
 * @author Denisse
 */
public interface ISmfMenuPrincipalUsersVServices {
    
    public List<IiceMenuPrincipalUsersV> obtenerMenuPrincipal(String usr , int idRol);
    
}
