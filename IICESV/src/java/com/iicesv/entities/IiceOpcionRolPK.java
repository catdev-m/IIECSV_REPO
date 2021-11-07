/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author irvin_monterroza
 */
@Embeddable
public class IiceOpcionRolPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opcion")
    private int idOpcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;

    public IiceOpcionRolPK() {
    }

    public IiceOpcionRolPK(int idOpcion, int idRol) {
        this.idOpcion = idOpcion;
        this.idRol = idRol;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOpcion;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceOpcionRolPK)) {
            return false;
        }
        IiceOpcionRolPK other = (IiceOpcionRolPK) object;
        if (this.idOpcion != other.idOpcion) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceOpcionRolPK[ idOpcion=" + idOpcion + ", idRol=" + idRol + " ]";
    }
    
}
