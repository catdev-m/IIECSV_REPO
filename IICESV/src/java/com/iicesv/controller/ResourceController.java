/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.controller;
import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.services.IResourceServices;
import com.iicesv.entities.IiceResourceCatalog;
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
/**
 *
 * @author Jonathan Chavez
 */
@Named(value = "resourceController")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceController extends Utils implements Serializable {
    private int indexRowSelectedFormResource=-1;
    private String formResourceNombre;
    private String formResourceDescripcion;
    private String formResourcePrecio;
    private Date formFechaCreacion;
    private Date selectFecha;
    private String obtenerFecha;
    private String formResourceEstado;
    private List<IiceResourceCatalog> listRecursos;
    private IiceResourceCatalog selectedResource;
    private IResourceServices iSmfResourceServices;
    private boolean selectedRowsResource = false;

    public ResourceController() {
    }
    
    @PostConstruct
    public void init() {

        loadContextBeanSring();
        

        clearFormResource();
    }
    
    public void loadContextBeanSring() {

        iSmfResourceServices = ApplicationContextProvider.getApplicationContext().getBean(IResourceServices.class);

    }
        public void selectedRowsResource() throws ParseException {
        formResourceNombre = selectedResource.getNombre();
        formResourceDescripcion=selectedResource.getDescripcion();
        formResourcePrecio=selectedResource.getPrecio();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        obtenerFecha=selectedResource.getFechaCreacion();
        selectFecha=formato.parse(obtenerFecha);
        formFechaCreacion = selectFecha;
        formResourceEstado = selectedResource.getEstado();
        selectedRowsResource = true;
        

    }
        
    public String fecha(){
    SimpleDateFormat formato=new SimpleDateFormat("dd/MM/YYYY");
    return formato.format(formFechaCreacion);
    }
    public String guardarResource() {
        try {

            if (validNullString(formResourceNombre)) {

                addSimpleMessagesError("Debe de digitar el nombre del material");
                return null;
            }
            if (validNullString(formResourceDescripcion)) {
                addSimpleMessagesError("se debe digitar una descripcion");
                return null;
            }
            if (validNullString(formResourcePrecio)) {

                addSimpleMessagesError("Debe de digitar el precio del material");
                return null;
            }
            if (formFechaCreacion==null) {
                addSimpleMessagesError("Debe de seleccionar una fecha");
                return null;
            }
            if (validNullString(formResourceEstado)) {
                addSimpleMessagesError("debe de seleccionar estado");
                return null;
            }
            IiceResourceCatalog opc = new IiceResourceCatalog();
            opc.setNombre(formResourceNombre);
            opc.setDescripcion(formResourceDescripcion);
            opc.setPrecio(formResourcePrecio);
            opc.setEstado(formResourceEstado);
            opc.setFechaCreacion(fecha());
            if (selectedRowsResource) {
                opc.setId(selectedResource.getId());
            } else {
                opc.setId(iSmfResourceServices.nextId());
            }

            iSmfResourceServices.guardar(opc);
            addSimpleMessages("Datos guardados con exito");
            clearFormResource();

        } catch (Exception e) {

            addSimpleMessagesError("se genero un error al intentar guardar los datos");

        }
        return null;
    }

    public void clearFormResource() {
        indexRowSelectedFormResource=-1;
        formResourceNombre = "";
        formResourceDescripcion = "";
        formResourcePrecio = "";
        formResourceEstado="";
        formFechaCreacion=null;
        selectedResource  = new IiceResourceCatalog();
        listRecursos = new ArrayList<>();
        listRecursos = iSmfResourceServices.obtenerResourceCatalogs();
        selectedRowsResource = false;
    }
    public String limpiarResource() {
        clearFormResource();
        return null;
    }
}
