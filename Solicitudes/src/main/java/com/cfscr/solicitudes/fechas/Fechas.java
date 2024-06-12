/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.fechas;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author pablo.elizondo
 */
public class Fechas {
    //CAPTURA FECHA ACTUAL
    public String fechaActual(){
        Date fecha = Date.from(Instant.now());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(fecha);
    }
    
    //CASTEAR STRING A DATE DE TIPO SQL
    public java.sql.Date stringToDate(String fecha){
        java.sql.Date date = java.sql.Date.valueOf(fecha);
        return date;
    }
}
