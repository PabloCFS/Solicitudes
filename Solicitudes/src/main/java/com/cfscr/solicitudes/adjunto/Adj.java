/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.adjunto;

import com.cfscr.solicitudes.entities.Adjunto;

import com.cfscr.solicitudes.service.ServiceAdjuntoImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author pablo.elizondo
 */
public class Adj {
    
    public Adj(){ }
    
    public void insertar(String pRuta, String pArch, int idSolicitud, int idMensaje) throws IOException{
        
        ServiceAdjuntoImpl servAdjuntoImpl = new ServiceAdjuntoImpl();
        String nombre = "";
        String extension = "";
        Adjunto miAdj = new Adjunto();
        byte[] bytes = null;
        
        File doc = new File(pRuta+pArch);
        
        if(doc.exists()){
            bytes = Files.readAllBytes(doc.toPath());
        } else {
            System.out.println("El archivo no existe");
        }
        
        String[] parts = pArch.split("\\.");
        for(int i=0; i<parts.length; i++){
            if(i < parts.length-1){
                nombre = nombre + parts[i];
            } else if(i == parts.length-1){
                extension = extension + parts[i];
            }
        }
        
        miAdj.setNombreArchivo(nombre);
        miAdj.setArchivo(bytes);
        miAdj.setExtension(extension);
        miAdj.setIdSolicitud(idSolicitud);
        miAdj.setIdMensaje(idMensaje);
        
        servAdjuntoImpl.insertar(miAdj);
    }
}
