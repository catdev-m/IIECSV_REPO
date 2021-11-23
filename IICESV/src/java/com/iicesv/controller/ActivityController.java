/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.controller;
import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.services.IActivityServices;
import com.iicesv.entities.IiceActivityCatalog;
import com.iicesv.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 *
 * @author User
 */
@Named(value = "activityController")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)

public class ActivityController extends Utils implements Serializable {
    private int indexRowSelectedFormActivity=-1;
    private String formActivityNombre;
    private String formActivityEstado;
    private List<IiceActivityCatalog> listActividades;
    private IiceActivityCatalog selectedActivity;
    private IActivityServices iSmfActivityServices;
    private boolean selectedRowsActivity = false;

    public ActivityController() {
    }
    
    @PostConstruct
    public void init() {

        loadContextBeanSring();
        

        clearFormActivity();
    }
    
    public void loadContextBeanSring() {

        iSmfActivityServices = ApplicationContextProvider.getApplicationContext().getBean(IActivityServices.class);

    }
        public void selectedRowsActivity() {
        formActivityNombre = selectedActivity.getNombre_actividad();
        formActivityEstado = selectedActivity.getEstado();
        selectedRowsActivity = true;
        

    }

    public String guardarActivity() {
        try {

            if (validNullString(formActivityNombre)) {

                addSimpleMessagesError("Debe de digitar una actividad");
                return null;
            }
            
            if (validNullString(formActivityEstado)) {
                addSimpleMessagesError("Debe de seleccionar estado");
                return null;
            }
            IiceActivityCatalog opc = new IiceActivityCatalog();
            opc.setNombre_actividad(formActivityNombre);
            opc.setEstado(formActivityEstado);
            if (selectedRowsActivity) {
                 opc.setId_actividad(selectedActivity.getId_actividad());
            } else {
                opc.setId_actividad(iSmfActivityServices.nextId());
            }

            iSmfActivityServices.guardar(opc);
            addSimpleMessages("Datos guardados con exito");
            clearFormActivity();

        } catch (Exception e) {

            addSimpleMessagesError("Se genero un error al intentar guardar los datos");

        }
        return null;
    }
    
    public void eliminarActivity(){
        
    }

    public void clearFormActivity() {
        indexRowSelectedFormActivity=-1;
        formActivityNombre = "";
        formActivityEstado="";
        selectedActivity  = new IiceActivityCatalog();
        listActividades = new ArrayList<>();
        listActividades = iSmfActivityServices.obtenerActivityCatalogs();
        selectedRowsActivity = false;
    }
    public String limpiarActivity() {
        clearFormActivity();
        return null;
    }
}
