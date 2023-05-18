/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.entities;

import java.sql.Date;

/**
 *
 * @author pablo.elizondo
 */
public class Mensaje {
    //Declaracion de variables
    int id;
    String descripcion;
    int idSolicitud;
    int idUsuarioComenta;
    Date fechaCreacion;
    
    //Constructor con y sin parametros
    public Mensaje(){
        this.id = 0;
        this.descripcion = "";
        this.idSolicitud = 0;
        this.idUsuarioComenta = 0;
        this.fechaCreacion = null;
    }
    
    public Mensaje(int pId, String pDescripcion, int pIdSolicitud, int pIdUsuarioComenta, Date pFechaCreacion){
        this.id = pId;
        this.descripcion = pDescripcion;
        this.idSolicitud = pIdSolicitud;
        this.idUsuarioComenta = pIdUsuarioComenta;
        this.fechaCreacion = pFechaCreacion;
    }
    
    //SETS
    public void setIdMensaje(int pId) { this.id = pId; }
    public void setSolicitud(String pDescripcion) { this.descripcion = pDescripcion; }
    public void setIdSolicitud(int pIdSolicitud) { this.idSolicitud = pIdSolicitud; }
    public void setIdUsuarioComenta(int pIdUsuarioComenta) { this.idUsuarioComenta = pIdUsuarioComenta;}
    public void setFechaCreacion(Date  pFechaCreacion) { this.fechaCreacion = pFechaCreacion; }

    //GETS
    public int getId() { return this.id; }
    public String getDescripcion() { return this.descripcion; }
    public int getIdSolicitud() { return this.idSolicitud; }
    public int getIdUsuarioComenta() { return this.idUsuarioComenta; }
    public Date getFechaCreacion() { return this.fechaCreacion; }
}
