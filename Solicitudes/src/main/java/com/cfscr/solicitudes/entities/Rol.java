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
public class Rol {
    private int id;
    private String nombre;
    
    public Rol(){
        this.id = 0;
        this.nombre = "";
    }
    
    public Rol(int pId, String pNombre){
        this.id = pId;
        this.nombre = pNombre;
    }

    public void setId(int id) { this.id = id; } 
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
}
