/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import java.util.ArrayList;

import com.cfscr.solicitudes.entities.Adjunto;
/**
 *
 * @author pablo.elizondo
 */
public interface ServiceAdjunto {
    
    //INSERTAR ADJUNTO
    void insertar(Adjunto pAdjunto);
    
    //ELIMINAR ADJUNTO
    boolean eliminar(int pAdjunto);

    //LISTAR ADJUNTOS
    ArrayList<Adjunto> listarAdjuntos(ArrayList<Adjunto> pAdjuntos, int pIdSolicitud);

}
