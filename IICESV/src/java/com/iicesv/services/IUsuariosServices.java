/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceUsuarios;

import java.util.List;

/**
 *
 * @author irvin_monterroza
 */
public interface IUsuariosServices{
    
    public IiceUsuarios getUsuariosValido(String usuario);    
    public void guardarUsuario(IiceUsuarios usr);
    public void eliminarUsuario(IiceUsuarios usr);
    public void actualizarUsuario(IiceUsuarios usr);
    public List<IiceUsuarios> obtenerUsuarios();
    public List<IiceUsuarios> obtenerUsuariosActivos();
    public int nextId();
    
    
}
