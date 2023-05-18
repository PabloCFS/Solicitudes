/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.entities.Solicitud;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public interface ServiceSolicitud {
    //INSERTAR SOLICITUD
    void insertar(Solicitud pSolicitud);
    
    //ELIMINAR SOLICITUD
    boolean eliminar(int pIdSolicitud);
    
    //LISTAR SOLICITUD
    ArrayList<Solicitud> listar(ArrayList<Solicitud> pSolicitud, String pTipoLista, int pIdUsuario);
    
    //CONSULTAR SOLICITUD
    Solicitud consultar(int pId);
    
    //ACTUALIZAR SOLICITUD
    boolean actualizar(Solicitud pSolicitud);
}
