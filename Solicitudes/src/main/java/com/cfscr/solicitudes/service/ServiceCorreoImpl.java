/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.correo.Correo;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceCorreoImpl implements ServiceCorreoExt{

    private final Correo correo = new Correo();
            
    @Override
    public void enviarCorreo(int tipoNotificacion, String receiver1, String receiver2, int solicitud, String titulo) {
        correo.enviarCorreo(tipoNotificacion, receiver1, receiver2, solicitud, titulo);
    }
}
