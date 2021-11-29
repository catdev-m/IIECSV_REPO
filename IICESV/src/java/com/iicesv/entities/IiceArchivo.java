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
import javax.persistence.Lob;
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
@Table(name = "iice_archivo")
@NamedQueries({
    @NamedQuery(name = "IiceArchivo.findAll", query = "SELECT i FROM IiceArchivo i")
    , @NamedQuery(name = "IiceArchivo.findByIdArchivo", query = "SELECT i FROM IiceArchivo i WHERE i.idArchivo = :idArchivo")
    , @NamedQuery(name = "IiceArchivo.findByIdInspeccion", query = "SELECT i FROM IiceArchivo i WHERE i.idInspeccion = :idInspeccion")
    , @NamedQuery(name = "IiceArchivo.findByDescripcion", query = "SELECT i FROM IiceArchivo i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceArchivo.findByTipoArchivo", query = "SELECT i FROM IiceArchivo i WHERE i.tipoArchivo = :tipoArchivo")
    , @NamedQuery(name = "IiceArchivo.findByFechaCreacion", query = "SELECT i FROM IiceArchivo i WHERE i.fechaCreacion = :fechaCreacion")})
public class IiceArchivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_archivo")
    private Integer idArchivo;
    @Column(name = "id_inspeccion")
    private Integer idInspeccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_archivo")
    private String tipoArchivo;
    @Lob
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "extension_archivo")
    private String extensionArchivo;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public IiceArchivo() {
    }

    public IiceArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public IiceArchivo(Integer idArchivo, String descripcion, String tipoArchivo) {
        this.idArchivo = idArchivo;
        this.descripcion = descripcion;
        this.tipoArchivo = tipoArchivo;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Integer getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(Integer idInspeccion) {
        this.idInspeccion = idInspeccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivo != null ? idArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceArchivo)) {
            return false;
        }
        IiceArchivo other = (IiceArchivo) object;
        if ((this.idArchivo == null && other.idArchivo != null) || (this.idArchivo != null && !this.idArchivo.equals(other.idArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceArchivo[ idArchivo=" + idArchivo + " ]";
    }
    
}
