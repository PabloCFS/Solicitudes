/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.connection.DAOMensaje;
import com.cfscr.solicitudes.entities.Mensaje;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceMensajeImpl implements ServiceMensajeExt{
    
    private final DAOMensaje daoMensaje = new DAOMensaje();

    //INSERTAR MENSAJE
    @Override
    public void insertar(Mensaje pMensaje) {
        daoMensaje.insertar(pMensaje);
    }

    //ELIMINAR MENSAJE
    @Override
    public boolean eliminar(int pId) {
        return daoMensaje.eliminar(pId);
    }

    //LISTAR MENSAJE
    @Override
    public ArrayList<Mensaje> listar(ArrayList<Mensaje> pMensaje) {
        return daoMensaje.listar(pMensaje);
    }

    //CONSULTAR MENSAJE
    @Override
    public Mensaje consultar(int pId) {
        return daoMensaje.consultar(pId);
    }

    //ACTUALIZAR MENSAJE
    @Override
    public boolean actualizar(Mensaje pMensaje) {
        return daoMensaje.actualizar(pMensaje);
    }
}