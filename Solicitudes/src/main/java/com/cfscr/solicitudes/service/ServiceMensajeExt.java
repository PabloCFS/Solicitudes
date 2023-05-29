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
public interface ServiceMensajeExt extends ServiceMensaje{
    //INSERTAR MENSAJE
    @Override
    void insertar(Mensaje pMensaje);
    
    //ELIMINAR MENSAJE
    @Override
    boolean eliminar(int pId);
    
    //LISTAR MENSAJE
    @Override
    ArrayList<Mensaje> listar(ArrayList<Mensaje> pMensaje);
    
    //LISTAR MENSAJES SOLICITUD
    @Override
    ArrayList<Mensaje> listarMensajes(ArrayList<Mensaje> pMensaje, int pId_Solicitud);
    
    //CONSULTAR MENSAJE
    @Override
    Mensaje consultar(int pId);
    
    //ACTUALIZAR MENSAJE
    @Override
    boolean actualizar(Mensaje pMensaje);
}
