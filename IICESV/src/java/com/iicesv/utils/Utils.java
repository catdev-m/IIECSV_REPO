/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iicesv.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Denisse
 */
public class Utils implements Serializable {

    private String currentUserInit;

    public static String getMessegesBundles(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(key);
        return message;
    }

    public void addMessagesWarn(String messages) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utils.getMessegesBundles(messages), ""));
    }

    public void addMessagesOk(String messages) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Utils.getMessegesBundles(messages), ""));
    }

    public void addMessagesError(String messages) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utils.getMessegesBundles(messages), ""));
    }

    public void addSimpleMessages(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    public void addSimpleMessagesWarn(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
    }

    public void addSimpleMessagesError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
    }

    public boolean validMail(String mail) {
        String pattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(mail);
        return m.find();
    }

    /**
     *
     * @return genera un codigo basado en UUID
     */
    public String codeSec() {
        String code = UUID.randomUUID().toString();
        String[] codeList = code.split("-");
        String codeSec = codeList[0] + "-" + codeList[1];
        return codeSec;
    }

    /**
     *
     * @param text Es el texto que se quiere embeber en el codigoQR
     * @param width Ancho que se quiere el codigoQR
     * @param height Alto que se quiere el codigoQR
     * @return regresa un String en base64 para poder dibujar el codigo QR en la
     * pagina web forma de usarlo URL =
     * data:image/png;base64,[AGREGAR_STRING_EN_BASE64]
     * @throws com.google.zxing.WriterException
     * @throws java.io.IOException
     */
    public String getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        String qrCode = Base64.getEncoder().encodeToString(pngData);
        //System.out.println(qrCode);
        return qrCode;
    }

    public String getCurrentUser() {
        String user = "";
        Authentication ctx = SecurityContextHolder.getContext().getAuthentication();

        if (ctx != null) {
            user = (String) ctx.getPrincipal();
        }
        return user;
    }

    /**
     *
     * @param field se ingresa el String que se quiere validar
     * @return regresa true si el String es vacio o null
     */
    public boolean validNullString(String field) {
        boolean nul = false;
        if (field == null || field.equals("")) {
            nul = true;
        }
        return nul;
    }

    public boolean validNullObject(Object field) {
        boolean nul = false;
        if (field == null) {
            nul = true;
        }
        return nul;
    }

    public static java.util.Date addMonths(java.util.Date today, int monthsToAdd) {
        if (today != null) {
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.MONTH, monthsToAdd);
            return c.getTime();
        } else {
            return null;
        }
    }

    public String datePattern() {
        String format = "dd/MM/yyyy";
        return format;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        return response;
    }

    /**
     *
     * @param nombrePDf nombre del archivo pdf que se genera para el usuario
     * final ejemplo: <code>mi_archivo.pdf</code>
     * @param nombreReporteJasper hace referencia al archivo compilado .jasper,
     * ejemplo reporte.jasper
     * @param hashMap variable que contendra todos los paramteros que pide el
     * reporte jasper
     * @param dataSource contiene la conexion de la base de datos cuando el
     * reporte tiene configurada una conexion para obtener datos
     */
    public void generarReportePDF(String nombrePDf, String nombreReporteJasper, HashMap<String, Object> hashMap, Connection dataSource) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=" + nombrePDf + ".pdf");
        InputStream reportStream = this.getClass().getClassLoader().getResourceAsStream(nombreReporteJasper);
        ServletOutputStream outputStream;

        try {
            outputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(reportStream, outputStream, hashMap, dataSource);
            response.setContentType("application/pdf");
            outputStream.flush();
            outputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int avisoDeCorrelativoProximoATerminar(int rangoFinal, int porcentajeDeAviso) {
        int aviso = Math.round((rangoFinal * porcentajeDeAviso) / 100);
        return aviso;

    }

    public Double formatDouble2Decimal(Double valor) {
        Double numero2Decimales = Double.valueOf(new DecimalFormat("#.##").format(valor));
        return numero2Decimales;
    }

    public String getCurrentUserInit() {
        return currentUserInit;
    }

    public void setCurrentUserInit(String currentUserInit) {
        this.currentUserInit = currentUserInit;
    }

}
