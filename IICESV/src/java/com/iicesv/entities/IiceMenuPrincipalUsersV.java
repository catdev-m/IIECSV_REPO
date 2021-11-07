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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "iice_menu_principal_users_v")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceMenuPrincipalUsersV.findAll", query = "SELECT i FROM IiceMenuPrincipalUsersV i")
    , @NamedQuery(name = "IiceMenuPrincipalUsersV.findById", query = "SELECT i FROM IiceMenuPrincipalUsersV i WHERE i.id = :id")
    , @NamedQuery(name = "IiceMenuPrincipalUsersV.findByDescripcion", query = "SELECT i FROM IiceMenuPrincipalUsersV i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceMenuPrincipalUsersV.findByMenuIcon", query = "SELECT i FROM IiceMenuPrincipalUsersV i WHERE i.menuIcon = :menuIcon")
    , @NamedQuery(name = "IiceMenuPrincipalUsersV.findByUsr", query = "SELECT i FROM IiceMenuPrincipalUsersV i WHERE i.usr = :usr")
    , @NamedQuery(name = "IiceMenuPrincipalUsersV.findByIdRol", query = "SELECT i FROM IiceMenuPrincipalUsersV i WHERE i.idRol = :idRol")})
public class IiceMenuPrincipalUsersV implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "MENU_ICON")
    private String menuIcon;
    @Size(max = 255)
    @Column(name = "usr")
    private String usr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;

    public IiceMenuPrincipalUsersV() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}
