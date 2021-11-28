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
 * @author User
 */
@Entity
@Table(name = "iiec_activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceActivityCatalog.findAll", query = "SELECT i FROM IiceActivityCatalog i")
    , @NamedQuery(name = "IiceActivityCatalog.findById", query = "SELECT i FROM IiceActivityCatalog i WHERE i.id_actividad= :id_actividad")
    ,@NamedQuery(name = "IiceActivityCatalog.findByIdFase", query = "SELECT i FROM IiceActivityCatalog i WHERE i.id_fase= :id_fase")
    , @NamedQuery(name = "IiceActivityCatalog.findByNombre", query = "SELECT i FROM IiceActivityCatalog i WHERE i.nombre_actividad = :nombre_actividad")
    , @NamedQuery(name = "IiceActivityCatalog.findByFechaCreacion", query = "SELECT i FROM IiceActivityCatalog i WHERE i.fecha_de_creacion = :fecha_de_creacion")
    ,@NamedQuery(name = "IiceActivityCatalog.findByFechaModificacion", query = "SELECT i FROM IiceActivityCatalog i WHERE i.fecha_de_modificacion = :fecha_de_modificacion")
    , @NamedQuery(name = "IiceActivityCatalog.findByEstado", query = "SELECT i FROM IiceActivityCatalog i WHERE i.estado = :estado")})
public class IiceActivityCatalog implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_actividad")
    private Integer id_actividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fase")
    private Integer id_fase;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "nombre_actividad")
    private String nombre_actividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_creacion")
    @Temporal(TemporalType.DATE)
    private Date fecha_de_creacion;
    @Size(max = 45)
    @Column(name = "fecha_de_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fecha_de_modificacion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    public IiceActivityCatalog() {
    }

    public IiceActivityCatalog(Integer id) {
        this.id_actividad = id_actividad;
    }

    public IiceActivityCatalog(Integer id_actividad, String nombre) {
        this.id_actividad = id_actividad;
        this.nombre_actividad = nombre;
        
    }

    public Integer getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(Integer id_actividad) {
        this.id_actividad = id_actividad;
    }

    public Integer getId_fase() {
        return id_fase;
    }

    public void setId_fase(Integer id_fase) {
        this.id_fase = id_fase;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public Date getFecha_de_creacion() {
        return fecha_de_creacion;
    }

    public void setFecha_de_creacion(Date fecha_de_creacion) {
        this.fecha_de_creacion = fecha_de_creacion;
    }

    public Date getFecha_de_modificacion() {
        return fecha_de_modificacion;
    }

    public void setFecha_de_modificacion(Date fecha_de_modificacion) {
        this.fecha_de_modificacion = fecha_de_modificacion;
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
        hash += (id_actividad != null ? id_actividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceActivityCatalog)) {
            return false;
        }
        IiceActivityCatalog other = (IiceActivityCatalog) object;
        if ((this.id_actividad == null && other.id_actividad != null) || (this.id_actividad!= null && !this.id_actividad.equals(other.id_actividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceActivityCatalog[ id=" + id_actividad + " ]";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
