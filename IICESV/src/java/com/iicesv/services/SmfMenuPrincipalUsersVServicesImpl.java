/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceMenuPrincipalUsersV;
import com.iicesv.repository.ISmfMenuPrincipalUsersVRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author irvin_monterroza
 */
@Service
public class SmfMenuPrincipalUsersVServicesImpl implements ISmfMenuPrincipalUsersVServices,Serializable{

    @Autowired
    ISmfMenuPrincipalUsersVRepository iSmfMenuPrincipalUsersVRepository;
    
    @Override
    public List<IiceMenuPrincipalUsersV> obtenerMenuPrincipal(String usr, int idRol) {
        return iSmfMenuPrincipalUsersVRepository.obtenerMenuPrincipal(usr, idRol);
    }
    
}
