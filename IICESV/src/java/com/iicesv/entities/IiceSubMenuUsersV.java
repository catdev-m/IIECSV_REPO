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
@Table(name = "iice_sub_menu_users_v")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IiceSubMenuUsersV.findAll", query = "SELECT i FROM IiceSubMenuUsersV i")
    , @NamedQuery(name = "IiceSubMenuUsersV.findById", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.id = :id")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByDescripcion", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByStatus", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.status = :status")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByMenuIcon", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.menuIcon = :menuIcon")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByUrl", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.url = :url")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByIdOpcPpal", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.idOpcPpal = :idOpcPpal")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByUsr", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.usr = :usr")
    , @NamedQuery(name = "IiceSubMenuUsersV.findByIdRol", query = "SELECT i FROM IiceSubMenuUsersV i WHERE i.idRol = :idRol")})
public class IiceSubMenuUsersV implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "menu_icon")
    private String menuIcon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opc_ppal")
    private int idOpcPpal;
    @Size(max = 255)
    @Column(name = "usr")
    private String usr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;

    public IiceSubMenuUsersV() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdOpcPpal() {
        return idOpcPpal;
    }

    public void setIdOpcPpal(int idOpcPpal) {
        this.idOpcPpal = idOpcPpal;
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
