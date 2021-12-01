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
 * @author nivrist
 */
@Entity
@Table(name = "iice_proyecto_actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceProyectoActividades.findAll", query = "SELECT i FROM IiceProyectoActividades i")
    , @NamedQuery(name = "IiceProyectoActividades.findById", query = "SELECT i FROM IiceProyectoActividades i WHERE i.id = :id")
    , @NamedQuery(name = "IiceProyectoActividades.findByIdActividad", query = "SELECT i FROM IiceProyectoActividades i WHERE i.idActividad = :idActividad")
    , @NamedQuery(name = "IiceProyectoActividades.findByIdFase", query = "SELECT i FROM IiceProyectoActividades i WHERE i.idFase = :idFase")
    , @NamedQuery(name = "IiceProyectoActividades.findByIdProyecto", query = "SELECT i FROM IiceProyectoActividades i WHERE i.idProyecto = :idProyecto")
    , @NamedQuery(name = "IiceProyectoActividades.findByFechaCreacion", query = "SELECT i FROM IiceProyectoActividades i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "IiceProyectoActividades.findByUsuarioCreacion", query = "SELECT i FROM IiceProyectoActividades i WHERE i.usuarioCreacion = :usuarioCreacion")})
public class IiceProyectoActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_actividad")
    private Integer idActividad;
    @Column(name = "id_fase")
    private Integer idFase;
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Size(max = 150)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    public IiceProyectoActividades() {
    }

    public IiceProyectoActividades(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
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
        if (!(object instanceof IiceProyectoActividades)) {
            return false;
        }
        IiceProyectoActividades other = (IiceProyectoActividades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceProyectoActividades[ id=" + id + " ]";
    }
    
}
