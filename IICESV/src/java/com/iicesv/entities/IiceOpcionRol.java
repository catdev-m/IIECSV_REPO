/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "iice_opcion_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceOpcionRol.findAll", query = "SELECT i FROM IiceOpcionRol i")
    , @NamedQuery(name = "IiceOpcionRol.findByIdOpcion", query = "SELECT i FROM IiceOpcionRol i WHERE i.iiceOpcionRolPK.idOpcion = :idOpcion")
    , @NamedQuery(name = "IiceOpcionRol.findByIdRol", query = "SELECT i FROM IiceOpcionRol i WHERE i.iiceOpcionRolPK.idRol = :idRol")})
public class IiceOpcionRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IiceOpcionRolPK iiceOpcionRolPK;

    public IiceOpcionRol() {
    }

    public IiceOpcionRol(IiceOpcionRolPK iiceOpcionRolPK) {
        this.iiceOpcionRolPK = iiceOpcionRolPK;
    }

    public IiceOpcionRol(int idOpcion, int idRol) {
        this.iiceOpcionRolPK = new IiceOpcionRolPK(idOpcion, idRol);
    }

    public IiceOpcionRolPK getIiceOpcionRolPK() {
        return iiceOpcionRolPK;
    }

    public void setIiceOpcionRolPK(IiceOpcionRolPK iiceOpcionRolPK) {
        this.iiceOpcionRolPK = iiceOpcionRolPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iiceOpcionRolPK != null ? iiceOpcionRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceOpcionRol)) {
            return false;
        }
        IiceOpcionRol other = (IiceOpcionRol) object;
        if ((this.iiceOpcionRolPK == null && other.iiceOpcionRolPK != null) || (this.iiceOpcionRolPK != null && !this.iiceOpcionRolPK.equals(other.iiceOpcionRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceOpcionRol[ iiceOpcionRolPK=" + iiceOpcionRolPK + " ]";
    }
    
}
