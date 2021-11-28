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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Irvin
 */
@Entity
@Table(name = "iice_ejecucion_financiera")
@NamedQueries({
    @NamedQuery(name = "IiceEjecucionFinanciera.findAll", query = "SELECT i FROM IiceEjecucionFinanciera i")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findById", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.id = :id")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByIdProyecto", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.idProyecto = :idProyecto")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByPresupuesto", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.presupuesto = :presupuesto")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByNDesembolso", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.nDesembolso = :nDesembolso")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByDesembolso", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.desembolso = :desembolso")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByRetenido", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.retenido = :retenido")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByEstado", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.estado = :estado")
    , @NamedQuery(name = "IiceEjecucionFinanciera.findByFechaDesembolso", query = "SELECT i FROM IiceEjecucionFinanciera i WHERE i.fechaDesembolso = :fechaDesembolso")})
public class IiceEjecucionFinanciera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "presupuesto")
    private long presupuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "n_desembolso")
    private int nDesembolso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "desembolso")
    private long desembolso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "retenido")
    private long retenido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_desembolso")
   @Temporal(TemporalType.DATE)
    private Date fechaDesembolso;

    public IiceEjecucionFinanciera() {
    }

    public IiceEjecucionFinanciera(Integer id) {
        this.id = id;
    }

    public IiceEjecucionFinanciera(Integer id, int idProyecto, long presupuesto, int nDesembolso, long desembolso, long retenido, String estado, Date fechaDesembolso) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.presupuesto = presupuesto;
        this.nDesembolso = nDesembolso;
        this.desembolso = desembolso;
        this.retenido = retenido;
        this.estado = estado;
        this.fechaDesembolso = fechaDesembolso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public long getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(long presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getNDesembolso() {
        return nDesembolso;
    }

    public void setNDesembolso(int nDesembolso) {
        this.nDesembolso = nDesembolso;
    }

    public long getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(long desembolso) {
        this.desembolso = desembolso;
    }

    public long getRetenido() {
        return retenido;
    }

    public void setRetenido(long retenido) {
        this.retenido = retenido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
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
        if (!(object instanceof IiceEjecucionFinanciera)) {
            return false;
        }
        IiceEjecucionFinanciera other = (IiceEjecucionFinanciera) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceEjecucionFinanciera[ id=" + id + " ]";
    }
    
}
