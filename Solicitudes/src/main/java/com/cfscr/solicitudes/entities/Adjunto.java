/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.entities;

/**
 *
 * @author pablo.elizondo
 */
public class Adjunto {
    //Declaración de variables
    int idAdjunto;
    String nombreArchivo;
    byte[] archivo;
    String extension;
    int idSolicitud;
    int idMensaje;
    
    //Constructor con y sin parametros
    public Adjunto(){
        this.idAdjunto = 0;
        this.nombreArchivo = "";
        this.archivo = null;
        this.extension = "";
        this.idSolicitud = 0;
        this.idMensaje = 0;
    }
    
    public Adjunto(String pNombreArchivo, byte[] pArchivo, String pExtension, int pIdSolicitud, int pIdMensaje){
        this.nombreArchivo = pNombreArchivo;
        this.archivo = pArchivo;
        this.extension = pExtension;
        this.idSolicitud = pIdSolicitud;
        this.idMensaje = pIdMensaje;
    }
    
    public Adjunto(int pIdAjunto, String pNombreArchivo, byte[] pArchivo, String pExtension, int pIdSolicitud, int pIdMensaje){
        this.idAdjunto = pIdAjunto;
        this.nombreArchivo = pNombreArchivo;
        this.archivo = pArchivo;
        this.extension = pExtension;
        this.idSolicitud = pIdSolicitud;
        this.idMensaje = pIdMensaje;
    }
    
    //SETS
    public void setAdjunto(int pIdAdjunto) { this.idAdjunto = pIdAdjunto; }
    public void setNombreArchivo(String pNombreArchivo) { this.nombreArchivo = pNombreArchivo; }
    public void setArchivo(byte[] pArchivo) { this.archivo = pArchivo; }
    public void setExtension(String pExtension) { this.extension = pExtension; }
    public void setIdSolicitud(int pIdSolicitud) { this.idSolicitud = pIdSolicitud; }
    public void setIdMensaje(int pIdMensaje) { this.idMensaje = pIdMensaje; }
    
    //GETS
    public int getIdAdjunto() { return this.idAdjunto; }
    public String getNombreArchivo() { return this.nombreArchivo; }
    public byte[] getArchivo() { return this.archivo; }
    public String getExtension() { return this.extension; }
    public int getIdSolicitud() { return this.idSolicitud; }
    public int getIdMensaje() { return this.idMensaje; }
}
