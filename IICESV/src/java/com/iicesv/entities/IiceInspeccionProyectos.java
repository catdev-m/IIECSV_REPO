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

/**
 *
 * @author Denisse
 */
@Entity
@Table(name = "iice_inspeccion_proyectos")
@NamedQueries({
    @NamedQuery(name = "IiceInspeccionProyectos.findAll", query = "SELECT i FROM IiceInspeccionProyectos i")
    , @NamedQuery(name = "IiceInspeccionProyectos.findByIdInspeccion", query = "SELECT i FROM IiceInspeccionProyectos i WHERE i.idInspeccion = :idInspeccion")
    , @NamedQuery(name = "IiceInspeccionProyectos.findByIdProyecto", query = "SELECT i FROM IiceInspeccionProyectos i WHERE i.idProyecto = :idProyecto")
    , @NamedQuery(name = "IiceInspeccionProyectos.findByEstado", query = "SELECT i FROM IiceInspeccionProyectos i WHERE i.estado = :estado")
    , @NamedQuery(name = "IiceInspeccionProyectos.findByFechaInspeccion", query = "SELECT i FROM IiceInspeccionProyectos i WHERE i.fechaInspeccion = :fechaInspeccion")
    , @NamedQuery(name = "IiceInspeccionProyectos.findByNombreProyecto", query = "SELECT i FROM IiceInspeccionProyectos i WHERE i.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "IiceInspeccionProyectos.findByObservaciones", query = "SELECT i FROM IiceInspeccionProyectos i WHERE i.observaciones = :observaciones")})
public class IiceInspeccionProyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_inspeccion")
    private Integer idInspeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Size(max = 255)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_inspeccion")
    @Temporal(TemporalType.DATE)
    private Date fechaInspeccion;
    @Size(max = 255)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;

    public IiceInspeccionProyectos() {
    }

    public IiceInspeccionProyectos(Integer idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public IiceInspeccionProyectos(Integer idInspeccion, int idProyecto) {
        this.idInspeccion = idInspeccion;
        this.idProyecto = idProyecto;
    }

    public Integer getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(Integer idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInspeccion() {
        return fechaInspeccion;
    }

    public void setFechaInspeccion(Date fechaInspeccion) {
        this.fechaInspeccion = fechaInspeccion;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInspeccion != null ? idInspeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceInspeccionProyectos)) {
            return false;
        }
        IiceInspeccionProyectos other = (IiceInspeccionProyectos) object;
        if ((this.idInspeccion == null && other.idInspeccion != null) || (this.idInspeccion != null && !this.idInspeccion.equals(other.idInspeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceInspeccionProyectos[ idInspeccion=" + idInspeccion + " ]";
    }
    
}
