/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.controller;

import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.entities.IiceRolUsuario;
import com.iicesv.entities.IiceUsuarios;
import com.iicesv.services.IIicesvRolServices;
import com.iicesv.services.ISmfRolUsuarioServices;
import com.iicesv.services.IUsuariosServices;
import com.iicesv.utils.Utils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.primefaces.model.menu.BaseMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;

/**
 *
 * @author Denisse
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Named(value = "loginSecurity")
@SessionScoped
public class LoginSecurity extends Utils implements Serializable {

    /**
     * Creates a new instance of LoginSecurity
     */
    private MenuModel model;
    
    private int indexRowSelectedFormUsuarios=-1;

    private String formUser;
    private String formPassword;

    /*Formulario de creacion de usuarios Variables*/
    private String formUsrNobreCompleto;
    private String formUsrUserApp;
    private String formUsrPassword;
    private String formUsrCorreoElectronico;
    private String formUsreEstado;
    private int selectedRoleUsr;
    private boolean selectedRowUsr = false;
    private ISmfRolUsuarioServices iSmfRolUsuarioServices;
    private IiceUsuarios selectedRowUser;
    private List<IiceUsuarios> listUsr;
    private List<SelectItem> listRoles;

    public LoginSecurity() {
    }

    private IUsuariosServices iUsuariosServices;

    private IIicesvRolServices iSmfRolServices;

    @PostConstruct
    public void init() {
        loadContextBeanSring();
        clearFormUsers();
        listRoles = new ArrayList<>();
        iSmfRolServices.obtenerRoles().forEach((obj) -> {
            listRoles.add(new SelectItem(obj.getId(), obj.getDescripcion()));
        });
    }

    public String loadSelectedRow() {
        formUsrCorreoElectronico = selectedRowUser.getCorreoElectronico();
        formUsrNobreCompleto = selectedRowUser.getNombre();
        formUsrPassword = selectedRowUser.getPassword();
        formUsrUserApp = selectedRowUser.getUsr();
        formUsreEstado = selectedRowUser.getEstado();
        selectedRoleUsr = (iSmfRolUsuarioServices.obtenerUsuarioROl(selectedRowUser.getId()).getIdRol());
        selectedRowUsr = true;
        return null;
    }

    public String ingresar() {

        try {

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward(request, response);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (IOException | ServletException e) {

        }
        return null;
    }

    private SessionRegistry sessionRegistry;

    public void logOut() {
        try {

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                String currentPrincipalName = authentication.getName();
                sessionRegistry.getAllPrincipals().stream().filter((principal) -> (currentPrincipalName.equals(principal))).map((principal) -> {
                    sessionRegistry.getAllSessions(principal, true).stream().map((information) -> {
                        information.expireNow();
                        return information;
                    }).forEachOrdered((information) -> {
                        sessionRegistry.removeSessionInformation(information.getSessionId());
                    });
                    return principal;
                }).forEachOrdered((_item) -> {
                    authentication.setAuthenticated(false);
                });
            }
            model = new BaseMenuModel();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logout");
            dispatcher.forward(request, response);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (ServletException | IOException ex) {
        }

    }

    public String guardarUsuario() {
        int idUser = 0;
        int idRolUsuario = 0;
        if (validNullString(formUsrUserApp)) {
            addSimpleMessagesError("Usuario de aplicacion es requerido");
            return null;
        }
        if (validNullString(formUsrNobreCompleto)) {
            addSimpleMessagesError("Nombre completo es requerido");
            return null;
        }
        if (validNullString(formUsrPassword)) {
            addSimpleMessagesError("Clave es requerido");
            return null;
        }

        if (validNullString(formUsrCorreoElectronico)) {
            addSimpleMessagesError("Correo Elecntronico es requerido");
            return null;
        }
        if (selectedRoleUsr == 0) {
            addSimpleMessagesError("Debe de seleccionar un Rol");
            return null;
        }

        if (validNullString(formUsreEstado)) {
            addSimpleMessagesError("Debe de seleccionar un Estado");
            return null;
        }
        try {
            IiceUsuarios usr = new IiceUsuarios();

            if (selectedRowUsr) {
                idUser = selectedRowUser.getId();
                idRolUsuario = iSmfRolUsuarioServices.obtenerUsuarioROl(idUser).getId();
            } else {
                idUser = iUsuariosServices.nextId();
                idRolUsuario = iSmfRolUsuarioServices.nextId();
            }
            usr.setCorreoElectronico(formUsrCorreoElectronico);
            usr.setEstado(formUsreEstado);
            usr.setId(idUser);
            usr.setNombre(formUsrNobreCompleto);
            usr.setPassword(formUsrPassword);
            usr.setUsr(formUsrUserApp);
            iUsuariosServices.guardarUsuario(usr);

            IiceRolUsuario rolUser = new IiceRolUsuario();
            rolUser.setId(idRolUsuario);
            rolUser.setIdRol(selectedRoleUsr);
            rolUser.setIdUser(idUser);
            iSmfRolUsuarioServices.guardarUsuarioRol(rolUser);
            addSimpleMessages(" Usuario guardado con exito");
            clearFormUsers();
        } catch (NumberFormatException e) {

            addMessagesError("Se genero un error al intentar guardar el usuario!");

        }

        return null;
    }

    public String limpiarUsuarios() {
        clearFormUsers();
        return null;
    }

    public void clearFormUsers() {
        formUsrCorreoElectronico = "";
        formUsrNobreCompleto = "";
        formUsrPassword = "";
        formUsrUserApp = "";
        indexRowSelectedFormUsuarios=-1;
        formUsreEstado = "";
        selectedRowUser = new IiceUsuarios();
        selectedRoleUsr = 0;
        selectedRowUsr = false;
        listUsr = new ArrayList<>();
        listUsr = iUsuariosServices.obtenerUsuarios();

    }

    public void loadContextBeanSring() {
        iUsuariosServices = ApplicationContextProvider.getApplicationContext().getBean(IUsuariosServices.class);
        iSmfRolUsuarioServices = ApplicationContextProvider.getApplicationContext().getBean(ISmfRolUsuarioServices.class);
        iSmfRolServices = ApplicationContextProvider.getApplicationContext().getBean(IIicesvRolServices.class);
        sessionRegistry = ApplicationContextProvider.getApplicationContext().getBean(SessionRegistry.class);

    }
}
