/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.entities.Mensaje;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public interface ServiceMensaje {
    //INSERTAR MENSAJE
    void insertar(Mensaje pMensaje);
    
    //ELIMINAR MENSAJE
    boolean eliminar(int pId);
    
    //LISTAR MENSAJE
    ArrayList<Mensaje> listar(ArrayList<Mensaje> pMensaje);
    
    //CONSULTAR MENSAJE
    Mensaje consultar(int pId);
    
    //ACTUALIZAR MENSAJE
    boolean actualizar(Mensaje pMensaje);
}
