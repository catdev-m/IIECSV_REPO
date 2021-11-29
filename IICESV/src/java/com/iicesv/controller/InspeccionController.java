package com.iicesv.controller;

import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.dto.ListaProyectosDto;
import com.iicesv.dto.ProyectoActividadDTO;
import com.iicesv.entities.IiceArchivo;
import com.iicesv.services.IInspeccionServices;
import com.iicesv.entities.IiceInspeccionProyectos;
import com.iicesv.entities.IiceProyecto;
import com.iicesv.services.IArchivoService;
import com.iicesv.services.IProyectoService;
import com.iicesv.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
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
 import​ javax.faces.model.SelectItem​;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.file.UploadedFile;

@Named(value = "inspeccionController")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class InspeccionController extends Utils implements Serializable {

    private int indexRowSelectedFormInspeccion = -1;
    private IArchivoService iArchivoService;
    private String formProyectoNombre;
    private Date formFechaInspeccion;
    private String obtenerFecha;
    private Date selectFecha;
    private String formObservaciones;
    private String formInspeccionEstado;
    private List<IiceInspeccionProyectos> listInspecciones;
    private List<IiceProyecto> listProyectos;
    private IiceInspeccionProyectos selectedInspeccion;
    private IInspeccionServices iSmfInspeccionServices;
    private List<ListaProyectosDto> listadoProyectos;
    private List<SelectItem> listaForm;
    private IProyectoService iProyectoService;
    private Utils user;
    private int formSelectedProyectoMultimedia;
    private UploadedFile file;
    private String descripcionArchivo;
    private List<IiceArchivo> listaMultimedia;
    private int formIdProyecto;
    private String nombre;
    private boolean selectedRowsInspeccion = false;

    public InspeccionController() {
    }

    @PostConstruct
    public void init() {

        loadContextBeanSring();
        clearFormInspeccion();

        listaForm = new ArrayList<>();

        iProyectoService.cargarProyectos().forEach((obj) -> {
            listaForm.add(new SelectItem(obj.getId_proy(), obj.getDescripcion()));
        });

        listaMultimedia = new ArrayList<>();
        listaMultimedia = iArchivoService.obtenerArchivos();
        loadDataProyectos();
    }

    public String obtenerUser() {
        nombre = getCurrentUser();
        return nombre;
    }

    public void loadContextBeanSring() {

        iSmfInspeccionServices = ApplicationContextProvider.getApplicationContext().getBean(IInspeccionServices.class);
        iProyectoService = ApplicationContextProvider.getApplicationContext().getBean(IProyectoService.class);
        iArchivoService = ApplicationContextProvider.getApplicationContext().getBean(IArchivoService.class);
    }

    public void selectedRowsInspeccion() throws ParseException {
        formProyectoNombre = selectedInspeccion.getNombreProyecto();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //obtenerFecha=selectedInspeccion.getFechaInspeccion();
        //selectFecha=formato.parse(obtenerFecha);
        formFechaInspeccion = selectedInspeccion.getFechaInspeccion();
        formObservaciones = selectedInspeccion.getObservaciones();
        formInspeccionEstado = selectedInspeccion.getEstado();
        selectedRowsInspeccion = true;
    }

    /* public Date fecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(formFechaInspeccion);
    }*/
    public void limpiarFormMultimedia() {
        formSelectedProyectoMultimedia = 0;
        descripcionArchivo = "";
        file = null;
        listaMultimedia = new ArrayList<>();
        listaMultimedia = iArchivoService.obtenerArchivos();
    }

    public String guardarMultimedia() {

        try {

            if (formSelectedProyectoMultimedia == 0) {
                addSimpleMessagesError("Debe de seleccionar un proyecto");
                return null;
            }

            if (validNullString(descripcionArchivo)) {

                addSimpleMessagesError("Debe de completar el campo Descripcion de archivo");
                return null;

            }

            if (file.getSize() <= 0) {

                addSimpleMessagesError("Debe de seleccionar un archivo");
                return null;

            }
            InputStream finput = file.getInputStream();
            byte[] imageBytes = new byte[(int) file.getSize()];
            finput.read(imageBytes, 0, imageBytes.length);
            finput.close();
            String imageStr = Base64.encodeBase64String(imageBytes);
            String str = file.getFileName();
            String ext = str.substring(str.lastIndexOf('.'), str.length());
            IiceArchivo ar = new IiceArchivo();
            ar.setIdArchivo(iArchivoService.nextId());
            ar.setDescripcion(descripcionArchivo);
            ar.setFechaCreacion(new Date());
            ar.setIdInspeccion(formSelectedProyectoMultimedia);
            ar.setArchivo(imageStr);
            ar.setExtensionArchivo(ext);
            ar.setNombreArchivo(file.getFileName());
            ar.setUsuarioCreacion(getCurrentUser());
            ar.setTipoArchivo(file.getContentType());
            iArchivoService.guardarArchivo(ar);
            addSimpleMessages("Datos guardados con exito");
            limpiarFormMultimedia();

        } catch (IOException es) {
            addSimpleMessagesError("Se genero un error al intentar guardar los datos");
            es.printStackTrace();
        }

        return null;
    }

    public String guardarInspeccion() {
        try {

            if (validNullString(formProyectoNombre)) {
                addSimpleMessagesError("Debe de digitar el nombre del proyecto");
                return null;
            }
            if (formFechaInspeccion == null) {
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
            opc.setFechaInspeccion(formFechaInspeccion);
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

    //###### VARIABLES PARA PANTALLA PROYECTOS ################
    private String descripcionDeProyecto;
    private String selectedFaseProyecto;
    private List<SelectItem> litadoFases;
    private String selectedActividad;
    private String selectedRecurso;
    private String selectedDecripcionFase;
    private String selectedDecripcionActividad;
    private List<SelectItem> litadoActividades;
    private List<SelectItem> litadoRecursos;
    private List<ProyectoActividadDTO> listadoActividades;
    
    private List<IiceProyecto> listadoTablaProyecto;
    
    
    

    
    public void activarTabActividades(){
    
    litadoActividades = new ArrayList<>();
        List<Object[]> obj = iProyectoService.cargarActividadesByIdFase(Integer.parseInt(selectedFaseProyecto));
        for (Object[] val : obj) {
            litadoActividades.add(new SelectItem(val[0].toString(), val[1].toString()));

        }        
        selectedDecripcionFase = iProyectoService.obtenerNombreFase(Integer.parseInt(selectedFaseProyecto));
        
        
    
    }
    
    public void loadnombreActividad(){
     selectedDecripcionActividad = iProyectoService.obtenerNombreActividad(Integer.parseInt(selectedActividad));
    
    }
    
    
    
    public void agregarrecurso(){
    
    
    
    }
    
    
    public void agregarAvtividad(){
    if(listadoActividades == null){    
    listadoActividades = new ArrayList<>();
    }
    ProyectoActividadDTO dto = new ProyectoActividadDTO();
    dto.setIdActividad(Integer.parseInt(selectedActividad));
    dto.setIdFase(Integer.parseInt(selectedFaseProyecto));
    dto.setNombreActividad(iProyectoService.obtenerNombreActividad(Integer.parseInt(selectedActividad)));
    dto.setFechaCreacion(new Date());
    listadoActividades.add(dto);    
    }
    
    public void loadDataProyectos() {
        
        listadoTablaProyecto = new ArrayList<>();
        listadoTablaProyecto = iProyectoService.obtenerProyectos();        
        litadoFases = new ArrayList<>();
        List<Object[]> obj = iProyectoService.cargarFases();
        for (Object[] val : obj) {
            litadoFases.add(new SelectItem(val[0].toString(), val[1].toString()));

        }
        
        
        litadoRecursos = new ArrayList<>();
        List<Object[]> objR = iProyectoService.obtenerRecursos();
        for (Object[] val : objR) {
            litadoRecursos.add(new SelectItem(val[0].toString(), val[1].toString()));

        }

    }

}
