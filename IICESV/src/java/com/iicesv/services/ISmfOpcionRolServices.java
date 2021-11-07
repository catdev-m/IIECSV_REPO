/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceOpcionRol;

/**
 *
 * @author Denisse
 */
public interface ISmfOpcionRolServices {
    
    public void guardarOpcionRole(IiceOpcionRol opc);
    public void eliminarOpcionRole(IiceOpcionRol opc);
    public void actualizarOpcionRole(IiceOpcionRol opc);
    public void eliminarOpcionesPorRol(int idRol);
    
    
}
