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
 * @author irvin_monterroza
 */
@Entity
@Table(name = "iice_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceUsuarios.findAll", query = "SELECT i FROM IiceUsuarios i")
    , @NamedQuery(name = "IiceUsuarios.findById", query = "SELECT i FROM IiceUsuarios i WHERE i.id = :id")
    , @NamedQuery(name = "IiceUsuarios.findByNombre", query = "SELECT i FROM IiceUsuarios i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "IiceUsuarios.findByUsr", query = "SELECT i FROM IiceUsuarios i WHERE i.usr = :usr")
    , @NamedQuery(name = "IiceUsuarios.findByPassword", query = "SELECT i FROM IiceUsuarios i WHERE i.password = :password")
    , @NamedQuery(name = "IiceUsuarios.findByFechaCreacion", query = "SELECT i FROM IiceUsuarios i WHERE i.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "IiceUsuarios.findByFechaModificacion", query = "SELECT i FROM IiceUsuarios i WHERE i.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "IiceUsuarios.findByTwoFactAuth", query = "SELECT i FROM IiceUsuarios i WHERE i.twoFactAuth = :twoFactAuth")
    , @NamedQuery(name = "IiceUsuarios.findByCorreoElectronico", query = "SELECT i FROM IiceUsuarios i WHERE i.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "IiceUsuarios.findByEstado", query = "SELECT i FROM IiceUsuarios i WHERE i.estado = :estado")})
public class IiceUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "usr")
    private String usr;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Size(max = 400)
    @Column(name = "two_fact_auth")
    private String twoFactAuth;
    @Size(max = 400)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;

    public IiceUsuarios() {
    }

    public IiceUsuarios(Integer id) {
        this.id = id;
    }

    public IiceUsuarios(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTwoFactAuth() {
        return twoFactAuth;
    }

    public void setTwoFactAuth(String twoFactAuth) {
        this.twoFactAuth = twoFactAuth;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
        if (!(object instanceof IiceUsuarios)) {
            return false;
        }
        IiceUsuarios other = (IiceUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceUsuarios[ id=" + id + " ]";
    }
    
}
