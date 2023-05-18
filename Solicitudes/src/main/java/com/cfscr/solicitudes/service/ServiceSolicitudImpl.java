/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.connection.DAOSolicitud;
import com.cfscr.solicitudes.entities.Solicitud;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceSolicitudImpl implements ServiceSolicitudExt{
    private final DAOSolicitud daoSolicitud = new DAOSolicitud();

    //INSERTAR SOLICITUD
    @Override
    public void insertar(Solicitud pSolicitud) {
        daoSolicitud.insertar(pSolicitud);
    }

    //ELIMINAR SOLICITUD
    @Override
    public boolean eliminar(int pId) {
        return daoSolicitud.eliminar(pId);
    }

    //LISTAR SOLICITUD
    @Override
    public ArrayList<Solicitud> listar(ArrayList<Solicitud> pSolicitud, String pTipoLista, int pIdUsuario) {
        return daoSolicitud.listar(pSolicitud, pTipoLista, pIdUsuario);
    }

    //CONSULTAR SOLICITUD
    @Override
    public Solicitud consultar(int pId) {
        return daoSolicitud.consultar(pId);
    }

    //ACTUALIZAR SOLICITUD
    @Override
    public boolean actualizar(Solicitud pSolicitud) {
        return daoSolicitud.actualizar(pSolicitud);
    }
}