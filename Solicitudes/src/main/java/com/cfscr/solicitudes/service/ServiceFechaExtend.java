/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

/**
 *
 * @author pablo.elizondo
 */
public interface ServiceFechaExtend extends ServiceFecha{
        
    //CAPTURA FECHA ACTUAL
    @Override
    public String fechaActual();
     
    //CASTEAR STRING A DATE DE TIPO SQL
    @Override
    public java.sql.Date stringToDate(String fecha);
}
