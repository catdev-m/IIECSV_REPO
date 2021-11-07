/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "iice_opcion_principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceOpcionPrincipal.findAll", query = "SELECT i FROM IiceOpcionPrincipal i")
    , @NamedQuery(name = "IiceOpcionPrincipal.findById", query = "SELECT i FROM IiceOpcionPrincipal i WHERE i.id = :id")
    , @NamedQuery(name = "IiceOpcionPrincipal.findByDescripcion", query = "SELECT i FROM IiceOpcionPrincipal i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceOpcionPrincipal.findByMenuIcon", query = "SELECT i FROM IiceOpcionPrincipal i WHERE i.menuIcon = :menuIcon")
    , @NamedQuery(name = "IiceOpcionPrincipal.findByStatus", query = "SELECT i FROM IiceOpcionPrincipal i WHERE i.status = :status")})
public class IiceOpcionPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "menu_icon")
    private String menuIcon;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOpcPpal")
    private Collection<IiceOpcion> iiceOpcionCollection;

    public IiceOpcionPrincipal() {
    }

    public IiceOpcionPrincipal(Integer id) {
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

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<IiceOpcion> getIiceOpcionCollection() {
        return iiceOpcionCollection;
    }

    public void setIiceOpcionCollection(Collection<IiceOpcion> iiceOpcionCollection) {
        this.iiceOpcionCollection = iiceOpcionCollection;
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
        if (!(object instanceof IiceOpcionPrincipal)) {
            return false;
        }
        IiceOpcionPrincipal other = (IiceOpcionPrincipal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceOpcionPrincipal[ id=" + id + " ]";
    }
    
}
