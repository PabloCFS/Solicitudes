/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.entities.Adjunto;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */

public interface ServiceAdjuntoExtend extends ServiceAdjunto{
    //INSERTAR ADJUNTO
    @Override
    void insertar(Adjunto pAdjunto);
    
    //ELIMINAR ADJUNTO
    @Override
    boolean eliminar(int pEliminar);
    
    //LISTAR ADJUNTOS
    @Override
    ArrayList<Adjunto> listarAdjuntos(ArrayList<Adjunto> pAdjuntos, int pIdSolicitud);
}
