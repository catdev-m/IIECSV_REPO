/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.controller;

import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.entities.IiceIconValue;
import com.iicesv.entities.IiceOpcion;
import com.iicesv.entities.IiceOpcionPrincipal;
import com.iicesv.entities.IiceOpcionRol;
import com.iicesv.entities.IiceOpcionRolPK;
import com.iicesv.services.ISmfIconValueServices;
import com.iicesv.services.ISmfOpcionPrincipalServices;
import com.iicesv.services.ISmfOpcionRolServices;
import com.iicesv.services.ISmfOpcionServices;
import com.iicesv.services.IIicesvRolServices;
import com.iicesv.utils.Utils;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import lombok.Data;
import lombok.EqualsAndHashCode;



import org.primefaces.model.DualListModel;

/**
 *
 * @author Denisse
 */
@Named(value = "configMenuControllers")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigMenuControllers extends Utils implements Serializable {

    
    private int indexRowSelectedFormOpcionPrincipal=-1;
    private int indexRowSelectedFormOpciones=-1;
    private ISmfIconValueServices iSmfIconValueServices;
    private List<SelectItem> listaDeIconos;
    private List<IiceIconValue> listIcon;
    private IiceIconValue selectedIconOneMenu;
    private String selectedIcon;
    
    /*Variables formulario Opciones Principales*/
    private String formOpcPpDescripcion;
    private String formOpcPpEstado;
    private String formopcPpMenuIcon;
    private List<IiceOpcionPrincipal> listOpcionPrincipal;
    private IiceOpcionPrincipal selectedOpcionPrincipal;
    private ISmfOpcionPrincipalServices iSmfOpcionPrincipalServices;
    private boolean selectedRowsOpcionPrincipal = false;

    /*Variables formulario Opciones*/
    private ISmfOpcionServices iSmfOpcionServices;
    private String formOpcDescripcion;
    private String formOpcEstado;
    private String formOpcUrl;
    private String formOpcIcono;
    private String formOpcIdOpcionPrincipal;
    private List<IiceOpcion> listOpciones;
    private IiceOpcion selectedOpciones;
    private boolean selectedRowsOpcion = false;
    private String idSelectedOpcionPrincipal;
    private List<SelectItem> listOpcionesPrincipales;

    /*Variables para Opciones por Role*/
    private DualListModel<IiceOpcion> listModel;
    private List<IiceOpcion> opcinesDisponibles = new ArrayList<>();
    private List<IiceOpcion> opcinesAsignadas = new ArrayList<>();
    private IIicesvRolServices iSmfRolServices;
    private List<SelectItem> listRoles;
    private String selectedRole;
    private ISmfOpcionRolServices iSmfOpcionRolServices;

    /**
     * Creates a new instance of ConfigMenuControllers
     */
    @PostConstruct
    public void init() {

        loadContextBeanSring();
        listRoles = new ArrayList<>();
        iSmfRolServices.obtenerRoles().forEach((obj) -> {
            listRoles.add(new SelectItem(obj.getId(), obj.getDescripcion()));
        });
        

        clearFormOpcionPrincipal();
        clearFormOpciones();
        limpiarOpcionesRol();
        

    }

    public void loadIconos() {
        listIcon = new ArrayList<>();
        listIcon = iSmfIconValueServices.obetnerIconos();
    }

    public ConfigMenuControllers() {
    }

    public void loadContextBeanSring() {

        iSmfOpcionPrincipalServices = ApplicationContextProvider.getApplicationContext().getBean(ISmfOpcionPrincipalServices.class);
        iSmfOpcionServices = ApplicationContextProvider.getApplicationContext().getBean(ISmfOpcionServices.class);
        iSmfIconValueServices = ApplicationContextProvider.getApplicationContext().getBean(ISmfIconValueServices.class);
        iSmfRolServices = ApplicationContextProvider.getApplicationContext().getBean(IIicesvRolServices.class);
        iSmfOpcionRolServices = ApplicationContextProvider.getApplicationContext().getBean(ISmfOpcionRolServices.class);

    }

    public void selectedRowsOpcionPrincipal() {
        formOpcPpDescripcion = selectedOpcionPrincipal.getDescripcion();
        formOpcPpEstado = selectedOpcionPrincipal.getStatus();
        formopcPpMenuIcon = selectedOpcionPrincipal.getMenuIcon();
        selectedRowsOpcionPrincipal = true;
        

    }

    public String guardarOpcionPrincipal() {
        try {

            if (validNullString(formOpcPpDescripcion)) {

                addSimpleMessagesError("Debe de digitar el nombre de opcion principal");
                return null;
            }
            if (validNullString(formopcPpMenuIcon)) {
                addSimpleMessagesError("debe de seleccionar un icono");
                return null;
            }
            if (validNullString(formOpcPpEstado)) {
                addSimpleMessagesError("debe de seleccionar estado");
                return null;
            }
            IiceOpcionPrincipal opc = new IiceOpcionPrincipal();
            opc.setDescripcion(formOpcPpDescripcion);
            opc.setMenuIcon(formopcPpMenuIcon);
            opc.setStatus(formOpcPpEstado);
            if (selectedRowsOpcionPrincipal) {
                opc.setId(selectedOpcionPrincipal.getId());
            } else {
                opc.setId(iSmfOpcionPrincipalServices.nextId());
            }

            iSmfOpcionPrincipalServices.guardar(opc);
            addSimpleMessages("Datos guardados con exito");
            clearFormOpcionPrincipal();

        } catch (Exception e) {

            addSimpleMessagesError("se genero un error al intentar guardar los datos");

        }
        return null;
    }

    public void clearFormOpcionPrincipal() {
        indexRowSelectedFormOpcionPrincipal=-1;
        formOpcPpDescripcion = "";
        formOpcPpEstado = "";
        formopcPpMenuIcon = "";
        selectedOpcionPrincipal  = new IiceOpcionPrincipal();
        listOpcionPrincipal = new ArrayList<>();
        listOpcionPrincipal = iSmfOpcionPrincipalServices.obtenerOpcionPrincpal();
        selectedRowsOpcionPrincipal = false;
        loadIconos();

    }

    public String guardarOpciones() {

        IiceOpcion op = new IiceOpcion();

        if (validNullString(formOpcDescripcion)) {
            addSimpleMessagesError("debe de ingresar la descripcion");
            return null;
        }
        if (validNullString(formOpcUrl)) {
            addSimpleMessagesError("debe de ingresar la Url");
            return null;
        }

        if (validNullString(formOpcEstado)) {
            addSimpleMessagesError("debe de seleccionar un estado");
            return null;
        }

        if (validNullString(idSelectedOpcionPrincipal)) {
            addSimpleMessagesError("debe de seleccionar menu principal");
            return null;
        }

        try {
            op.setDescripcion(formOpcDescripcion);
            op.setMenuIcon(selectedIcon);
            op.setStatus(formOpcEstado);
            op.setUrl(formOpcUrl);
            op.setIdOpcPpal(iSmfOpcionPrincipalServices.findByIdOpcP(Integer.valueOf(idSelectedOpcionPrincipal)));
            if (selectedRowsOpcion) {
                op.setId(selectedOpciones.getId());
            } else {
                op.setId(iSmfOpcionServices.nextId());
            }
            iSmfOpcionServices.guardar(op);
            addSimpleMessages("Datos guardados con exito");
            clearFormOpciones();
            loadIconos();
        } catch (NumberFormatException e) {

            addSimpleMessagesError("se genero un erro al intentar guardar los cambios");

        }

        return null;
    }

    public void clearFormOpciones() {
        formOpcDescripcion = "";
        formOpcEstado = "";
        formOpcUrl = "";
        indexRowSelectedFormOpciones=-1;
        formOpcIcono = "";
        idSelectedOpcionPrincipal = "";
        listOpciones = new ArrayList<>();
        selectedOpciones = new IiceOpcion();
        selectedRowsOpcion = false;
        listOpciones = iSmfOpcionServices.obtenerOpciones();
        listOpcionesPrincipales = new ArrayList<>();
        iSmfOpcionPrincipalServices.obtenerOpcionPrincpal().forEach((obj) -> {
            listOpcionesPrincipales.add(new SelectItem(obj.getId(), obj.getDescripcion()));
        });
        selectedIconOneMenu = new IiceIconValue();
        selectedIcon = "";
        loadIconos();

    }

    public String limpiarOpciones() {
        clearFormOpciones();
        return null;
    }

    public String limpiarOpcionPrincipal() {
        clearFormOpcionPrincipal();
        return null;
    }

    public void selectedRowOpciones() {
        formOpcDescripcion = selectedOpciones.getDescripcion();
        formOpcEstado = selectedOpciones.getStatus();
        
        formOpcUrl = selectedOpciones.getUrl();
        selectedIcon = selectedOpciones.getMenuIcon();
        idSelectedOpcionPrincipal = String.valueOf(selectedOpciones.getIdOpcPpal().getId());
        selectedRowsOpcion = true;

    }

    public void loadDualList() {
        opcinesDisponibles = new ArrayList<>();
        opcinesAsignadas = new ArrayList<>();

        opcinesAsignadas = iSmfOpcionServices.opcionesAsignadasPorRol(Integer.parseInt(selectedRole));
        opcinesDisponibles = iSmfOpcionServices.opcionesDisponibles(Integer.parseInt(selectedRole));

        listModel = new DualListModel<>(new ArrayList<>(opcinesDisponibles), opcinesAsignadas);
    }

    public String limpiarOpcionesRol(){
    selectedRole="";
    opcinesDisponibles = new ArrayList<>();
    opcinesAsignadas = new ArrayList<>();
    listModel = new DualListModel<>(new ArrayList<>(opcinesDisponibles), opcinesAsignadas);
    
    return null;
    }

    public String guardarRoles() {

        if (validNullString(selectedRole)) {

            addSimpleMessagesError("Debe de seleccionar un rol");
            return null;

        }
        try {
                iSmfOpcionRolServices.eliminarOpcionesPorRol(Integer.parseInt(selectedRole));
                
            for (Object obj : listModel.getTarget()) {
                
                IiceOpcionRol opc = new IiceOpcionRol();
                IiceOpcionRolPK pk = new IiceOpcionRolPK();
                pk.setIdOpcion(Integer.valueOf(String.valueOf(obj)));
                pk.setIdRol(Integer.parseInt(selectedRole));
                opc.setIiceOpcionRolPK(pk);
                iSmfOpcionRolServices.guardarOpcionRole(opc);

            }
            addSimpleMessages("Opciones guardadas con exito!");
            loadDualList();
        } catch (NumberFormatException e) {
            addSimpleMessagesError("Se genero un error al intentar guardar los datos");
        }

        return null;

    }


}
