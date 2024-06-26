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
public class Usuario {
    //Declaracion de variables
    int id;
    String nombre;
    int rol;
    String password;
    String email;
    
    //Constructor sin y con parametros
    public Usuario(){
        this.id = 0;
        this.nombre = "";
        this.rol = 0;
        this.password = "";
        this.email = "";
    }
    
    public Usuario(int pId, String pNombre, int pRol, String pPassword, String pEmail){
        this.id = pId;
        this.nombre = pNombre;
        this.rol = pRol;
        this.password = pPassword;
        this.email = pEmail;
    }
    
    //SETS
    public void setId(int pId) { this.id = pId; }
    public void setNombre(String pNombre) { this.nombre = pNombre; }
    public void setRol(int pRol) { this.rol = pRol; }
    public void setPassword(String pPassword) { this.password = pPassword; }
    public void setEmail(String pEmail) { this.email = pEmail; }
    
    //GETS
    public int getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public int getRol() { return this.rol; }
    public String getPassword() { return this.password; }
    public String getEmail() { return this.email; }
}