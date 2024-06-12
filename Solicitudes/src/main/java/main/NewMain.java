/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.cfscr.solicitudes.connection.DAOAdjunto;
import com.cfscr.solicitudes.entities.Adjunto;
import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Prueba de envio de correos
        MailTest mailTest = new MailTest();
        mailTest.ejecutar();
        */
        
        // TODO code application logic here
        ServiceUsuarioImpl serv = new ServiceUsuarioImpl();
        //DAOUsuarios usu = new DAOUsuarios();
        Usuario usuario = serv.login(117270025, "117270025");
        
        /*ADJUNTOS*/
        DAOAdjunto daoAdjunto = new DAOAdjunto();
        
        /*String nombreArchivo = "OC-SS";
        
        String extension = "pdf";
        int idSolicitud = 2;
        int idMensaje = 1;
        
        File doc = new File("C:/Users/pablo.elizondo/Desktop/oc-ss.pdf");
        if(doc.exists()){
        } else {
            System.out.println("El archivo no existe");
        }
        
        String nombreArch = doc.getName();
        System.out.println("Nombre Arch -> "+nombreArch);
        
        byte[] bytes = Files.readAllBytes(doc.toPath());
        
        Adjunto miAdjunto = new Adjunto(nombreArchivo,bytes,extension,idSolicitud,idMensaje);
        daoAdjunto.insertar(miAdjunto);
        */
    
        /////////////////////////////////////////////////////////////////////////////////
   
        ArrayList<Adjunto> adjuntos = new ArrayList<Adjunto>();
        
        adjuntos = daoAdjunto.listarAdjuntos(adjuntos, 2);
        
        for(int i=0; i<adjuntos.size(); i++){
            
            System.out.println(adjuntos.get(i).getIdAdjunto());
            System.out.println(adjuntos.get(i).getNombreArchivo());
            System.out.println(adjuntos.get(i).getArchivo());
            System.out.println(adjuntos.get(i).getExtension());
            System.out.println(adjuntos.get(i).getIdSolicitud());
            System.out.println(adjuntos.get(i).getIdMensaje());
            
            System.out.println("\n=======================================");
            String doc = new String(adjuntos.get(i).getArchivo(), StandardCharsets.UTF_8);
            doc = "0x"+doc;
            System.out.println(doc);
            System.out.println("=======================================");
            
            String ruta = System.getProperty("C:/Users/pablo.elizondo/Desktop/");
            
            System.out.println("88");
            
            
            
            
            System.out.println("90");
            
            //FileWriter writer = new FileWriter(adjuntos.get(i).getArchivo().toString());
            FileWriter writer2 = new FileWriter(adjuntos.get(i).getNombreArchivo() +"."+ adjuntos.get(i).getExtension());
            
            System.out.println("92");
            //System.out.println("Writer ==> " + writer);
            
        }
    }
    
    private static void writeBytesToFile(String fileOutput, byte[] bytes)
        throws IOException {
        
       
    }
}