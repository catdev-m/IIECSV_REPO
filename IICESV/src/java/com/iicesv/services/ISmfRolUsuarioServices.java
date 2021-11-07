/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceRolUsuario;

/**
 *
 * @author irvin_monterroza
 */
public interface ISmfRolUsuarioServices {
    
    public void guardarUsuarioRol(IiceRolUsuario usrRol);
    public void eliminarUsuarioRol(IiceRolUsuario usrRol);
    public void actualizarUsuarioRol(IiceRolUsuario usrRol);
    public IiceRolUsuario obtenerUsuarioROl(int idUser);
    
    public int nextId();
}
