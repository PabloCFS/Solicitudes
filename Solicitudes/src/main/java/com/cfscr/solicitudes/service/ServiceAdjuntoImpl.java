/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.connection.DAOAdjunto;
import com.cfscr.solicitudes.entities.Adjunto;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceAdjuntoImpl {
    
    private final DAOAdjunto daoAdjunto = new DAOAdjunto();
    
    //INSERTAR ADJUNTO
    public void insertar(Adjunto pAdjunto){
        daoAdjunto.insertar(pAdjunto);
    }
    
    //ELIMINAR ADJUNTO
    public boolean eliminar(int pAdjunto){
        return daoAdjunto.eliminar(pAdjunto);
    }
    
    //LISTAR ADJUNTOS
    public ArrayList<Adjunto> listarAdjuntos(ArrayList<Adjunto> pAdjuntos, int pIdSolicitud){
        return daoAdjunto.listarAdjuntos(pAdjuntos, pIdSolicitud);
    }
}
