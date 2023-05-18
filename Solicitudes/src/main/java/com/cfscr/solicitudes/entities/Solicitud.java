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
public class Solicitud {
    //Declaracion de variables
    int id;
    String titulo;
    int idTipo;
    int idSolicitante;
    String descripcion;
    int estado;
    int idPropietario;
    Date fechaCreacion;
    Date fechaModificacion;
    
    //Constructor con y sin parametros
    public Solicitud(){
        this.id = 0;
        this.titulo = "";
        this.idTipo = 0;
        this.idSolicitante = 0;
        this.descripcion = "";
        this.estado = 0;
        this.idPropietario = 0;
        this.fechaCreacion = null;
        this.fechaModificacion = null;        
    }
    
    public Solicitud(int pId, String pTitulo, int pIdTipo, int pIdSolicitante, String pDescripcion, int pEstado, int pIdPropietario, Date pFechaCreacion, Date pFechaModificacion){
        this.id = pId;
        this.titulo = pTitulo;
        this.idTipo = pIdTipo;
        this.idSolicitante = pIdSolicitante;
        this.descripcion = pDescripcion;
        this.estado = pEstado;
        this.idPropietario = pIdPropietario;
        this.fechaCreacion = pFechaCreacion;
        this.fechaModificacion = pFechaModificacion;
    }
    
    //SETS
    public void setId(int pId) { this.id = pId; }
    public void setTitulo(String pTitulo) { this.titulo = pTitulo; }
    public void setTipo(int pIdTipo) { this.idTipo = pIdTipo; }
    public void setIdSolicitante(int pIdSolicitante) { this.idSolicitante = pIdSolicitante; }
    public void setDescripcion(String pDescripcion) { this.descripcion = pDescripcion; }
    public void setEstado(int pEstado) { this.estado = pEstado; }
    public void setIdPropietario(int pIdPropietario) { this.idPropietario = pIdPropietario; }
    public void setFechaCreacion(Date pFechaCreacion) { this.fechaCreacion = pFechaCreacion; }
    public void setFechaModificacion(Date pFechaModificacion) { this.fechaModificacion = pFechaModificacion; }
    
    //GETS
    public int getId() { return this.id; }
    public String getTitulo() { return this.titulo; }
    public int getIdTipo() { return this.idTipo; }
    public int getIdSolicitante() { return this.idSolicitante; }
    public String getDescripcion() { return this.descripcion; }
    public int getEstado() { return this.estado; }
    public int getIdPropietario() { return this.idPropietario; }
    public Date getFechaCreacion() { return this.fechaCreacion; }
    public Date getFechaModificacion() { return this.fechaModificacion; }
}
