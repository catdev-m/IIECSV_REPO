/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.services;

import com.iicesv.entities.IiceEjecucionFinanciera;
import java.util.List;

/**
 *
 * @author Irvin
 */
public interface iFinanzasServices {
    public IiceEjecucionFinanciera guardar(IiceEjecucionFinanciera bk);
    public IiceEjecucionFinanciera actualizar(IiceEjecucionFinanciera bk);
    public void eliminar(IiceEjecucionFinanciera bk);
    public List<IiceEjecucionFinanciera> obtenerEjecucionFinanciera();
    public IiceEjecucionFinanciera findByIdCatR(int pk);
    public int nextId();
}
