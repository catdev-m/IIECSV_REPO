/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Denisse
 */
@Entity
@Table(name = "iiec_fases")
@NamedQueries({
    @NamedQuery(name = "IiecFases.findAll", query = "SELECT i FROM IiecFases i")
    , @NamedQuery(name = "IiecFases.findByIdFase", query = "SELECT i FROM IiecFases i WHERE i.idFase = :idFase")
    , @NamedQuery(name = "IiecFases.findByFase", query = "SELECT i FROM IiecFases i WHERE i.fase = :fase")
    , @NamedQuery(name = "IiecFases.findByEstado", query = "SELECT i FROM IiecFases i WHERE i.estado = :estado")})
public class IiecFases implements Serializable {

    @OneToMany(mappedBy = "idFase")
    private Collection<IiceProyecto> iiceProyectoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fase")
    private Integer idFase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "fase")
    private String fase;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    public IiecFases() {
    }

    public IiecFases(Integer idFase) {
        this.idFase = idFase;
    }

    public IiecFases(Integer idFase, String fase) {
        this.idFase = idFase;
        this.fase = fase;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFase != null ? idFase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiecFases)) {
            return false;
        }
        IiecFases other = (IiecFases) object;
        if ((this.idFase == null && other.idFase != null) || (this.idFase != null && !this.idFase.equals(other.idFase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiecFases[ idFase=" + idFase + " ]";
    }

    @XmlTransient
    public Collection<IiceProyecto> getIiceProyectoCollection() {
        return iiceProyectoCollection;
    }

    public void setIiceProyectoCollection(Collection<IiceProyecto> iiceProyectoCollection) {
        this.iiceProyectoCollection = iiceProyectoCollection;
    }
    
}
