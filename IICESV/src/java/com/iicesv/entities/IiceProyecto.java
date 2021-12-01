/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "iice_proyecto")
@XmlRootElement
public class IiceProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "presupuesto")
    private BigDecimal presupuesto;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_fase", referencedColumnName = "id_fase")
    @ManyToOne
    private IiecFases idFase;
    @Column(name = "porc_avance")
    private Integer porcAvance;
    public IiceProyecto() {
    }

    public IiceProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public IiceProyecto(Integer idProyecto, String nombreProyecto, BigDecimal presupuesto, Date fechaCreacion, Date fechaModificacion, String estado) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.presupuesto = presupuesto;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
    }

    public Integer getPorcAvance() {
        return porcAvance;
    }

    public void setPorcAvance(Integer porcAvance) {
        this.porcAvance = porcAvance;
    }

    
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public IiecFases getIdFase() {
        return idFase;
    }

    public void setIdFase(IiecFases idFase) {
        this.idFase = idFase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IiceProyecto)) {
            return false;
        }
        IiceProyecto other = (IiceProyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceProyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
