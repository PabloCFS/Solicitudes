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
public class EstadoSolicitud {
    private int id;
    private String nombre;
    
    public EstadoSolicitud(){
        this.id = 0;
        this.nombre = "";
    }
    
    public EstadoSolicitud(int pId, String pNombre){
        this.id = pId;
        this.nombre = pNombre;
    }

    public void setId(int pId) { this.id = pId; }
    public void setNombre(String pNombre) { this.nombre = pNombre; }

    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
}
