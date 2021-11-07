/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceSubMenuUsersV;
import com.iicesv.repository.ISmfSubMenuUsersVReporsitory;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Denisse
 */
@Service
public class SmfSubMenuUsersVServicesImpl implements ISmfSubMenuUsersVServices,Serializable{

    @Autowired
    ISmfSubMenuUsersVReporsitory iSmfSubMenuUsersVReporsitory;
    
    @Override
    public List<IiceSubMenuUsersV> obtenerSubMenu(int idOpcPpal, String usr, int idRol) {
        return iSmfSubMenuUsersVReporsitory.obtenerSubMenu(idOpcPpal, usr, idRol);
    }
    
}
