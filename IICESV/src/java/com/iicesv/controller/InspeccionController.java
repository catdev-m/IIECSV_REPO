package com.iicesv.controller;

import com.iicesv.config.ApplicationContextProvider;
import com.iicesv.dto.ListaProyectosDto;
import com.iicesv.dto.ListadorecursosPorActividadDTO;
import com.iicesv.dto.ProyectoActividadDTO;
import com.iicesv.entities.IiceArchivo;
import com.iicesv.services.IInspeccionServices;
import com.iicesv.entities.IiceInspeccionProyectos;
import com.iicesv.entities.IiceProyecto;
import com.iicesv.entities.IiceProyectoActividades;
import com.iicesv.entities.IiceProyectoRecurso;
import com.iicesv.services.IArchivoService;
import com.iicesv.services.IFasesServices;
import com.iicesv.services.IIiceProyectoActividadesServices;
import com.iicesv.services.IIiceProyectoRecursoServices;
import com.iicesv.services.IProyectoService;
import com.iicesv.services.IResourceServices;
import com.iicesv.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
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
    private List<SelectItem> listaFormAvance;
    private IProyectoService iProyectoService;
    private IIiceProyectoActividadesServices iIiceProyectoActividadesServices;
    private IIiceProyectoRecursoServices iIiceProyectoRecursoServices;
    private IFasesServices iFasesServices;
    private Utils user;
    private IResourceServices iResourceServices;
    private int formSelectedProyectoMultimedia;
    private UploadedFile file;
    private String descripcionArchivo;
    private List<IiceArchivo> listaMultimedia;
    private int formIdProyecto;
    private String nombre;
    private boolean selectedRowsInspeccion = false;
    private int procentajeAvance;

    public InspeccionController() {
    }

    @PostConstruct
    public void init() {

        loadContextBeanSring();
        clearFormInspeccion();

        listaForm = new ArrayList<>();
        listaFormAvance = new ArrayList<>();

        iProyectoService.cargarProyectos().forEach((obj) -> {

            listaForm.add(new SelectItem(obj.getId_proy(), obj.getDescripcion()));

        });

        iProyectoService.obtenerProyectos().forEach((obj) -> {

            listaFormAvance.add(new SelectItem(obj.getIdProyecto(), obj.getNombreProyecto() + " - Fase: " + obj.getIdFase().getFase()));

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
        iResourceServices = ApplicationContextProvider.getApplicationContext().getBean(IResourceServices.class);
        iIiceProyectoActividadesServices = ApplicationContextProvider.getApplicationContext().getBean(IIiceProyectoActividadesServices.class);
        iIiceProyectoRecursoServices = ApplicationContextProvider.getApplicationContext().getBean(IIiceProyectoRecursoServices.class);
        iFasesServices = ApplicationContextProvider.getApplicationContext().getBean(IFasesServices.class);

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
    private BigDecimal formPresupuesto = BigDecimal.ZERO;
    ;
    private List<SelectItem> litadoFases;
    private String selectedActividad;
    private String selectedRecurso;
    private String selectedDecripcionFase;
    private String selectedDecripcionActividad;
    private List<SelectItem> litadoActividades;
    private int activeTabIndex = 0;
    private String estadoProyecto;
    private List<SelectItem> litadoRecursos;
    private List<ProyectoActividadDTO> listadoActividades;
    private List<ListadorecursosPorActividadDTO> listadoRecursosXActividad;
    private IiceProyecto selectedProyectPorcentaje;
    private List<IiceProyecto> listadoTablaProyecto;

    public String eliminarActividad(ProyectoActividadDTO elim) {

        if (iProyectoService.obtenerRecursosPorProyectoyActividad(elim.getIdProyecto(), elim.getIdActividad()).size() >= 1) {
            addSimpleMessagesError("Existen recursos relacionados a la actividad que intenta eliminar");
            return null;
        }
        iIiceProyectoActividadesServices.eliminar(iIiceProyectoActividadesServices.findByIdActividad(elim.getId()));
        actividadSelectedRow = 0;
        selectedDecripcionActividad = "";
        listadoActividades = iProyectoService.obtenerActividadesPorProyectoyFase(idProyectoSelected, idFaseProyectoSelected);
        addSimpleMessages("Actividad eliminada con exito");

        return null;
    }

    public String eliminarRecurso(ListadorecursosPorActividadDTO elim) {

        iIiceProyectoRecursoServices.eliminar(iIiceProyectoRecursoServices.findByIdRecurso(elim.getId()));
        listadoRecursosXActividad = iProyectoService.obtenerRecursosPorProyectoyActividad(proyectodSelectedRow, actividadSelectedRow);
        addSimpleMessages("Recurso eliminado con exito");
        return null;

    }

    private int idProyectoSelected = 0;
    private int idFaseProyectoSelected = 0;
    private String proyectoSeleccionadoRow = "";

    public void seleccionarTabActividad(IiceProyecto objs) {
        proyectoSeleccionadoRow = objs.getNombreProyecto();
        activeTabIndex = 1;
        idProyectoSelected = objs.getIdProyecto();
        idFaseProyectoSelected = objs.getIdFase().getIdFase();
        litadoActividades = new ArrayList<>();
        List<Object[]> obj = iProyectoService.cargarActividadesByIdFase(objs.getIdFase().getIdFase());
        for (Object[] val : obj) {
            litadoActividades.add(new SelectItem(val[0].toString(), val[1].toString()));

        }
        selectedDecripcionFase = iProyectoService.obtenerNombreFase(objs.getIdFase().getIdFase());
        listadoActividades = iProyectoService.obtenerActividadesPorProyectoyFase(objs.getIdProyecto(), objs.getIdFase().getIdFase());

    }

    private int actividadSelectedRow;
    private int proyectodSelectedRow;

    public String guardarPorcentajeAvance() {

        try {
            
            if(formSelectedProyectoMultimedia==0){
                addSimpleMessagesError("Debe de seleccionar un proyecto para  asignar un porcentaje de avance");
                return null;            
            }
            IiceProyecto obj = new IiceProyecto();
            obj = (selectedProyectPorcentaje == null ? iProyectoService.findByIdProyecto(formSelectedProyectoMultimedia) :selectedProyectPorcentaje);
            obj.setPorcAvance(procentajeAvance);
            iProyectoService.guardar(obj);
            listadoTablaProyecto = new ArrayList<>();
            listadoTablaProyecto = iProyectoService.obtenerProyectos();
            addSimpleMessages("Porcentaje de avance guardado con exito");
            procentajeAvance = 0;
            formSelectedProyectoMultimedia = 0;
            selectedProyectPorcentaje=null;
        } catch (Exception ex) {
            addSimpleMessagesError("se genero un error al intentar guardar");

            return null;
        }

        return null;
    }

    public void seleccionarTabRecurso(ProyectoActividadDTO dat) {
        selectedDecripcionActividad = iProyectoService.obtenerNombreActividad((dat.getIdActividad()));

        actividadSelectedRow = dat.getIdActividad();
        proyectodSelectedRow = dat.getIdProyecto();
        listadoRecursosXActividad = iProyectoService.obtenerRecursosPorProyectoyActividad(proyectodSelectedRow, actividadSelectedRow);

        activeTabIndex = 2;

    }

    public void activarTabActividades() {

        litadoActividades = new ArrayList<>();
        List<Object[]> obj = iProyectoService.cargarActividadesByIdFase(Integer.parseInt(selectedFaseProyecto));
        for (Object[] val : obj) {
            litadoActividades.add(new SelectItem(val[0].toString(), val[1].toString()));

        }
        selectedDecripcionFase = iProyectoService.obtenerNombreFase(Integer.parseInt(selectedFaseProyecto));

    }

    public String crearProyecto() {

        try {

            if (validNullString(descripcionDeProyecto)) {
                addSimpleMessagesError("Nombre del proyecto es requerido");
                return null;
            }

            if (validNullString(selectedFaseProyecto)) {
                addSimpleMessagesError("debe de seleccionar una fase");
                return null;
            }

            if (formPresupuesto.intValue() == 0) {
                addSimpleMessagesError("debe de ingresar el presupuesto");
                return null;
            }

            if (validNullString(estadoProyecto)) {
                addSimpleMessagesError("debe de seleccionar el estado del proyecto");
                return null;
            }

            int idProyecto = iProyectoService.nextId();
            IiceProyecto proy = new IiceProyecto();
            proy.setEstado(estadoProyecto);
            proy.setIdProyecto(idProyecto);
            proy.setFechaCreacion(new Date());
            proy.setIdFase(iFasesServices.obtenerFase(Integer.parseInt(selectedFaseProyecto)));
            proy.setNombreProyecto(descripcionDeProyecto);
            proy.setPresupuesto(formPresupuesto);

            iProyectoService.guardar(proy);
            addSimpleMessages("Proyecto guardado con exito.");
            descripcionDeProyecto = "";
            selectedFaseProyecto = "";
            estadoProyecto = "";
            formPresupuesto = BigDecimal.ZERO;
            loadDataProyectos();
        } catch (NumberFormatException ex) {
            addSimpleMessagesError("se genero un error al intentar guardar el proyecto");
            ex.printStackTrace();
            return null;
        }

        return null;
    }

    public void selectedProyectoPorcentaje() {
        if (selectedProyectPorcentaje.getPorcAvance() != null) {
            procentajeAvance = selectedProyectPorcentaje.getPorcAvance();
        } else {
            procentajeAvance = 0;
        }
        formSelectedProyectoMultimedia = selectedProyectPorcentaje.getIdProyecto();
    }

    public void loadnombreActividad() {
        selectedDecripcionActividad = iProyectoService.obtenerNombreActividad(Integer.parseInt(selectedActividad));

    }

    public String agregarrecurso() {
        if (listadoRecursosXActividad == null) {
            listadoRecursosXActividad = new ArrayList<>();
        }

        if (actividadSelectedRow == 0) {

            addSimpleMessagesError("Debe de seleccionar una actividad para poder agregar un recurso");
            return null;
        }
        if (validNullString(selectedRecurso)) {

            addSimpleMessagesError("Debe de seleccionar un recurso");
            return null;
        }
        try {
            IiceProyectoRecurso objs = new IiceProyectoRecurso();
            objs.setFechaCreacion(new Date());
            objs.setId(iIiceProyectoRecursoServices.nextId());
            objs.setIdActividad(actividadSelectedRow);
            objs.setIdProyecto(proyectodSelectedRow);
            objs.setIdRecurso(Integer.parseInt(selectedRecurso));
            objs.setUsuarioCreacion(getCurrentUser());
            iIiceProyectoRecursoServices.guardar(objs);
            listadoRecursosXActividad = iProyectoService.obtenerRecursosPorProyectoyActividad(proyectodSelectedRow, actividadSelectedRow);
            addSimpleMessages("recurso agregado con exito");
            selectedRecurso = "";
        } catch (NumberFormatException ex) {
            addSimpleMessagesError("se genero un error al instentar guardar");
            return null;
        }

        return null;
    }

    public String agregarAvtividad() {

        try {
            if (listadoActividades == null) {
                listadoActividades = new ArrayList<>();
            }

            if (idProyectoSelected == 0) {
                addSimpleMessagesError("Debe de seleccionar un proyecto para poder agregar actividades");
                return null;
            }

            if (validNullString(selectedActividad)) {

                addSimpleMessagesError("Debe de seleccionar una actividad");
                return null;
            }

            IiceProyectoActividades act = new IiceProyectoActividades();
            act.setFechaCreacion(new Date());
            act.setId(iIiceProyectoActividadesServices.nextId());
            act.setIdActividad(Integer.parseInt(selectedActividad));
            act.setIdFase((idFaseProyectoSelected));
            act.setIdProyecto(idProyectoSelected);
            act.setUsuarioCreacion(getCurrentUser());
            iIiceProyectoActividadesServices.guardar(act);
            addSimpleMessages("Actividad agregada con exito al proyecto");
            selectedActividad = "";
            listadoActividades = iProyectoService.obtenerActividadesPorProyectoyFase(idProyectoSelected, idFaseProyectoSelected);

        } catch (NumberFormatException ex) {
            addSimpleMessagesError("se genero un error al intentar guardar el proyecto");
            ex.printStackTrace();
            return null;
        }
        return null;
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
