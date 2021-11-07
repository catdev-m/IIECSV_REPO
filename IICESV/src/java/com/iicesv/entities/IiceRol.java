/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "iice_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceRol.findAll", query = "SELECT i FROM IiceRol i")
    , @NamedQuery(name = "IiceRol.findById", query = "SELECT i FROM IiceRol i WHERE i.id = :id")
    , @NamedQuery(name = "IiceRol.findByDescripcion", query = "SELECT i FROM IiceRol i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceRol.findByStatus", query = "SELECT i FROM IiceRol i WHERE i.status = :status")})
public class IiceRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "status")
    private String status;

    public IiceRol() {
    }

    public IiceRol(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceRol)) {
            return false;
        }
        IiceRol other = (IiceRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceRol[ id=" + id + " ]";
    }
    
}
