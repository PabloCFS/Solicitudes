/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.fechas.Fechas;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceFechaImpl {
    private final Fechas fechas = new Fechas();
    
        
    //CAPTURA FECHA ACTUAL
    public String fechaActual(){
        return fechas.fechaActual();
    };
     
    //CASTEAR STRING A DATE DE TIPO SQL
    public java.sql.Date stringToDate(String fecha){
        return fechas.stringToDate(fecha);
    };
}
