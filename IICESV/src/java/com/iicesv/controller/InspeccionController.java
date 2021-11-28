package com.iicesv.controller;

import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.services.IInspeccionServices;
import com.iicesv.entities.IiceInspeccionProyectos;
import com.iicesv.utils.Utils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Named(value = "inspeccionController")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class InspeccionController extends Utils implements Serializable {

    private int indexRowSelectedFormInspeccion = -1;
    private String formProyectoNombre;
    private Date formFechaInspeccion;
    private String obtenerFecha;
    private Date selectFecha;
    private String formObservaciones;
    private String formInspeccionEstado;
    private List<IiceInspeccionProyectos> listInspecciones;
    private IiceInspeccionProyectos selectedInspeccion;
    private IInspeccionServices iSmfInspeccionServices;
    private Utils user;
    private String nombre;
    private boolean selectedRowsInspeccion = false;
    public InspeccionController() {
    }

    @PostConstruct
    public void init() {

        loadContextBeanSring();
        clearFormInspeccion();
    }
public String obtenerUser(){
    nombre=getCurrentUser();
    return nombre;
}
    public void loadContextBeanSring() {

        iSmfInspeccionServices = ApplicationContextProvider.getApplicationContext().getBean(IInspeccionServices.class);

    }

    public void selectedRowsInspeccion() throws ParseException {
        formProyectoNombre = selectedInspeccion.getNombreProyecto();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        obtenerFecha=selectedInspeccion.getFechaInspeccion();
        selectFecha=formato.parse(obtenerFecha);
        formFechaInspeccion = selectFecha;
        formObservaciones = selectedInspeccion.getObservaciones();
        formInspeccionEstado = selectedInspeccion.getEstado();
        selectedRowsInspeccion = true;
    }

    public String fecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(formFechaInspeccion);
    }

    public String guardarInspeccion() {
        try {

            if (validNullString(formProyectoNombre)) {
                addSimpleMessagesError("Debe de digitar el nombre del proyecto");
                return null;
            }
            if (formFechaInspeccion==null) {
                addSimpleMessagesError("Se debe digitar una fecha");
                return null;
            }
            if (validNullString(formObservaciones)) {
                addSimpleMessagesError("Debe digitar las observaciones");
                return null;
            }
            if (validNullString(formInspeccionEstado)) {
                addSimpleMessagesError("Seleccione el estado de la inspeccion");
                return null;
            }
            IiceInspeccionProyectos opc = new IiceInspeccionProyectos();
            opc.setNombreProyecto(formProyectoNombre);
            opc.setFechaInspeccion(fecha());
            opc.setObservaciones(formObservaciones);
            opc.setEstado(formInspeccionEstado);
            if (selectedRowsInspeccion) {
                opc.setIdInspeccion(selectedInspeccion.getIdInspeccion());
            } else {
                opc.setIdInspeccion(iSmfInspeccionServices.nextId());
            }

            iSmfInspeccionServices.guardar(opc);
            addSimpleMessages("Datos guardados con exito");
            clearFormInspeccion();

        } catch (Exception e) {
            addSimpleMessagesError("Se genero un error al intentar guardar los datos");
        }
        return null;
    }

    public void clearFormInspeccion() {
        indexRowSelectedFormInspeccion = -1;
        formProyectoNombre = "";
        formFechaInspeccion = null;
        formObservaciones = "";
        formInspeccionEstado = "";
        selectedInspeccion = new IiceInspeccionProyectos();
        listInspecciones = new ArrayList<>();
        listInspecciones = iSmfInspeccionServices.obtenerInspeccion();
        selectedRowsInspeccion = false;
    }

    public String limpiarInspeccion() {
        clearFormInspeccion();
        return null;
    }
}
