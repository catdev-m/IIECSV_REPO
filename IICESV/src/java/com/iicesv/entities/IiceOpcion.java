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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "iice_opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceOpcion.findAll", query = "SELECT i FROM IiceOpcion i")
    , @NamedQuery(name = "IiceOpcion.findById", query = "SELECT i FROM IiceOpcion i WHERE i.id = :id")
    , @NamedQuery(name = "IiceOpcion.findByDescripcion", query = "SELECT i FROM IiceOpcion i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceOpcion.findByStatus", query = "SELECT i FROM IiceOpcion i WHERE i.status = :status")
    , @NamedQuery(name = "IiceOpcion.findByMenuIcon", query = "SELECT i FROM IiceOpcion i WHERE i.menuIcon = :menuIcon")
    , @NamedQuery(name = "IiceOpcion.findByUrl", query = "SELECT i FROM IiceOpcion i WHERE i.url = :url")})
public class IiceOpcion implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "menu_icon")
    private String menuIcon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "id_opc_ppal", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IiceOpcionPrincipal idOpcPpal;

    public IiceOpcion() {
    }

    public IiceOpcion(Integer id) {
        this.id = id;
    }

    public IiceOpcion(Integer id, String menuIcon, String url) {
        this.id = id;
        this.menuIcon = menuIcon;
        this.url = url;
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

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IiceOpcionPrincipal getIdOpcPpal() {
        return idOpcPpal;
    }

    public void setIdOpcPpal(IiceOpcionPrincipal idOpcPpal) {
        this.idOpcPpal = idOpcPpal;
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
        if (!(object instanceof IiceOpcion)) {
            return false;
        }
        IiceOpcion other = (IiceOpcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceOpcion[ id=" + id + " ]";
    }
    
}
