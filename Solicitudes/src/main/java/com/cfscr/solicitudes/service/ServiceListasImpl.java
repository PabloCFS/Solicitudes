/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.connection.DAOEstadoSolicitud;
import com.cfscr.solicitudes.connection.DAORol;
import com.cfscr.solicitudes.connection.DAOTipoSolicitud;
import com.cfscr.solicitudes.entities.EstadoSolicitud;
import com.cfscr.solicitudes.entities.Rol;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceListasImpl implements ServiceListasExt{

    private final DAOEstadoSolicitud daoEstSolicitud= new DAOEstadoSolicitud();
    private final DAORol daoRol = new DAORol();
    private final DAOTipoSolicitud daoTipoSol = new DAOTipoSolicitud();
    
    //LISTAR ESTADOS DE SOLICITUD
    @Override
    public ArrayList<EstadoSolicitud> listarEstado(ArrayList<EstadoSolicitud> lista) {
        return daoEstSolicitud.listar(lista);
    }

    //LISTAR ROLES
    @Override
    public ArrayList<Rol> listarRol(ArrayList<Rol> lista) {
        return daoRol.listar(lista);
    }

    //LISTAR TIPOS DE SOLICITUD
    @Override
    public ArrayList<TipoSolicitud> listarTipoSolicitud(ArrayList<TipoSolicitud> lista) {
        return daoTipoSol.listar(lista);
    }
}
