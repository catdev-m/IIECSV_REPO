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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "iice_rol_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceRolUsuario.findAll", query = "SELECT i FROM IiceRolUsuario i")
    , @NamedQuery(name = "IiceRolUsuario.findByIdUser", query = "SELECT i FROM IiceRolUsuario i WHERE i.idUser = :idUser")
    , @NamedQuery(name = "IiceRolUsuario.findByIdRol", query = "SELECT i FROM IiceRolUsuario i WHERE i.idRol = :idRol")
    , @NamedQuery(name = "IiceRolUsuario.findById", query = "SELECT i FROM IiceRolUsuario i WHERE i.id = :id")})
public class IiceRolUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    public IiceRolUsuario() {
    }

    public IiceRolUsuario(Integer id) {
        this.id = id;
    }

    public IiceRolUsuario(Integer id, int idUser, int idRol) {
        this.id = id;
        this.idUser = idUser;
        this.idRol = idRol;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof IiceRolUsuario)) {
            return false;
        }
        IiceRolUsuario other = (IiceRolUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iicesv.entities.IiceRolUsuario[ id=" + id + " ]";
    }
    
}
