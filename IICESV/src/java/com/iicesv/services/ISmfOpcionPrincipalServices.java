/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceOpcionPrincipal;
import java.util.List;

/**
 *
 * @author Denisse
 */
public interface ISmfOpcionPrincipalServices {
    
    public IiceOpcionPrincipal guardar(IiceOpcionPrincipal bk);
    public IiceOpcionPrincipal actualizar(IiceOpcionPrincipal bk);
    public void eliminar(IiceOpcionPrincipal bk);
    public List<IiceOpcionPrincipal> obtenerOpcionPrincpal();
    public IiceOpcionPrincipal findByIdOpcP(int pk);
    public int nextId();
    
}
