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
public interface ServiceSolicitudExt extends ServiceSolicitud{
    //INSERTAR SOLICITUD
    @Override
    void insertar(Solicitud pSolicitud);
    
    //ELIMINAR SOLICITUD
    @Override
    boolean eliminar(int pId);
    
    //LISTAR SOLICITUD
    @Override
    ArrayList<Solicitud> listar(ArrayList<Solicitud> pSolicitud, String pTipoLista, int pIdUsuario);
    
    //CONSULTAR SOLICITUD
    @Override
    Solicitud consultar(int pId);
    
    //ACTUALIZAR SOLICITUD
    @Override
    boolean actualizar(Solicitud pSolicitud);
}
