/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonathan Chavez
 */
@Entity
@Table(name = "iice_resource_catalog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceResourceCatalog.findAll", query = "SELECT i FROM IiceResourceCatalog i")
    , @NamedQuery(name = "IiceResourceCatalog.findById", query = "SELECT i FROM IiceResourceCatalog i WHERE i.id = :id")
    , @NamedQuery(name = "IiceResourceCatalog.findByNombre", query = "SELECT i FROM IiceResourceCatalog i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "IiceResourceCatalog.findByDescripcion", query = "SELECT i FROM IiceResourceCatalog i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceResourceCatalog.findByPrecio", query = "SELECT i FROM IiceResourceCatalog i WHERE i.precio = :precio")
    , @NamedQuery(name = "IiceResourceCatalog.findByFechaCreacion", query = "SELECT i FROM IiceResourceCatalog i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "IiceResourceCatalog.findByEstado", query = "SELECT i FROM IiceResourceCatalog i WHERE i.estado = :estado")})
public class IiceResourceCatalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "precio")
    private String precio;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    public IiceResourceCatalog() {
    }

    public IiceResourceCatalog(Integer id) {
        this.id = id;
    }

    public IiceResourceCatalog(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceResourceCatalog)) {
            return false;
        }
        IiceResourceCatalog other = (IiceResourceCatalog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceResourceCatalog[ id=" + id + " ]";
    }
    
}
