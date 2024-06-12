/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

/**
 *
 * @author pablo.elizondo
 */
public interface ServiceCorreoExt extends ServiceCorreo{
    //NOTIFICACIÓN POR CORREO
    @Override
    void enviarCorreo(int tipoNotificacion, String receiver1, String receiver2, int solicitud, String titulo);
}
