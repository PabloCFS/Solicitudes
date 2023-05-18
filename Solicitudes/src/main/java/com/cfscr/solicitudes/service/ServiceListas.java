/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.entities.EstadoSolicitud;
import com.cfscr.solicitudes.entities.Rol;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public interface ServiceListas {
    //LISTAR ESTADOS DE SOLICITUD
    ArrayList<EstadoSolicitud> listarEstado(ArrayList<EstadoSolicitud> lista);
    
    //LISTAR ROLES
    ArrayList<Rol> listarRol(ArrayList<Rol> lista);
    
    //LISTAR TIPOS DE SOLICITUDES
    ArrayList<TipoSolicitud> listarTipoSolicitud(ArrayList<TipoSolicitud> lista);
}