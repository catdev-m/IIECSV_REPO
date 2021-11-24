/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.controller;

import java.util.Date;
import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.entities.IiceEjecucionFinanciera;
import com.iicesv.services.iFinanzasServices;
import com.iicesv.entities.IiceEjecucionFinanciera;
import com.iicesv.services.iFinanzasServices;
import com.iicesv.utils.Utils;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import static org.primefaces.component.schedule.ScheduleBase.PropertyKeys.minTime;

/**
 *
 * @author Irvin
 */

@Named(value = "fiananzasController")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanzasController extends Utils implements Serializable {
    private int indexRowSelectedFormResource=-1;
    private double formFiananzasPresupuesto;
    private double formFiananzasDesembolso;
    private double formFiananzasRetenido;
    private String formFiananzasEstado;
    Date formFiananzasFecha = new Date(); 
    private List<IiceEjecucionFinanciera> listFinanzas;
    private IiceEjecucionFinanciera selectedFinanza;
    private iFinanzasServices iSmfFinanzasServices;
    private boolean selectedRowsResource = false;
      
    public FinanzasController() {
        
    }
     @PostConstruct
    public void init() {

        loadContextBeanSring();
        

        clearFormResource();
    }
    
    public void loadContextBeanSring() {

        iSmfFinanzasServices = ApplicationContextProvider.getApplicationContext().getBean(iFinanzasServices.class);

    }
        public void selectedRowsResource() {
        formFiananzasPresupuesto = selectedFinanza.getPresupuesto();
        formFiananzasDesembolso=selectedFinanza.getDesembolso();
        formFiananzasRetenido=selectedFinanza.getRetenido();
        formFiananzasEstado = selectedFinanza.getEstado();
        formFiananzasFecha = selectedFinanza.getFechaDesembolso();
        selectedRowsResource = true;
        

    }

    public String guardar() {
        try {
            
            if (formFiananzasPresupuesto == 0) {

                addSimpleMessagesError("Debe de digitar el presupuesto");
                return null;
            }
            if (formFiananzasDesembolso == 0) {

                addSimpleMessagesError("Debe de digitar el desembolso");
                return null;
            }
            if (formFiananzasRetenido == 0) {

                addSimpleMessagesError("Debe de digitar lo retenido");
                return null;
            }
            if (validNullString(formFiananzasEstado)) {
                addSimpleMessagesError("debe colocar estado");
                return null;
            }
            if (formFiananzasFecha == null) {

                addSimpleMessagesError("Debe colocar fecha");
                return null;
            }
            
        
            IiceEjecucionFinanciera opc = new IiceEjecucionFinanciera();
            opc.setPresupuesto((new Double (formFiananzasPresupuesto)).longValue());
            opc.setDesembolso((new Double (formFiananzasDesembolso)).longValue());
            opc.setRetenido((new Double (formFiananzasRetenido)).longValue());
            opc.setEstado(formFiananzasEstado);
            opc.setFechaDesembolso(formFiananzasFecha);
            

            
            if (selectedRowsResource) {
                opc.setId(selectedFinanza.getId());
            } else {
                opc.setId(iSmfFinanzasServices.nextId());
            }

            iSmfFinanzasServices.guardar(opc);
            addSimpleMessages("Datos guardados con exito");
            clearFormResource();

        } catch (Exception e) {

            addSimpleMessagesError("se genero un error al intentar guardar los datos");

        }
        return null;
    }

    public void clearFormResource() {
        indexRowSelectedFormResource=-1;
        formFiananzasDesembolso = 0;
        formFiananzasRetenido = 0;
        formFiananzasEstado = "";
      
     
        selectedFinanza  = new IiceEjecucionFinanciera();
        listFinanzas = new ArrayList<>();
        listFinanzas = iSmfFinanzasServices.obtenerEjecucionFinanciera();
        selectedRowsResource = false;
    }
    public String limpiar() {
        clearFormResource();
        return null;
    }
}
    

