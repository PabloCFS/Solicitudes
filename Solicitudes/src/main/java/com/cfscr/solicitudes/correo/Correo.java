/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.correo;

import org.simplejavamail.email.EmailBuilder;

import org.simplejavamail.mailer.MailerBuilder;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;

/**
 *
 * @author pablo.elizondo
 */
public class Correo {
    
    //Declaracion de variables
    private String asunto;
    private String cuerpo;
    
    //Constructores
    public Correo(){
        this.asunto = "";
        this.cuerpo = "";
    }
    
    public Correo(String pAsunto, String pCuerpo){
        this.asunto = pAsunto;
        this.cuerpo = pCuerpo;
    }
    
    //SET's
    public void setAsunto(String pAsunto) { this.asunto = pAsunto; }
    public void setCuerpo(String pCuerpo) { this.cuerpo = pCuerpo; }
    
    //GET's
    public String getAsunto() { return this.asunto; }
    public String getCuerpo() { return this.cuerpo; }
    
    /*  
        0) Nuevo Caso
        1) Actualizacion de Caso
        2) Cierre de Caso
        3) Nuevo Comentario
    */
    public void enviarCorreo(int tipoNotificacion, String receiver1, String receiver2, int solicitud, String titulo){
        switch(tipoNotificacion){
            case 0:
                setAsunto("Solicitudes CFS - Nueva Solicitud #"+solicitud+" - "+titulo);
                setCuerpo("Se creó una nueva solicitud en el portal de solicitudes CFS \n"
                    + "Solicitud:  #"+solicitud +" - "+titulo +"\n"
                    + "Asignado A : "+receiver1 +"\n"
                    + "Solicitante: "+receiver2 +"\n"
                    + "Saludos");
                break;
            case 1:
                setAsunto("Solicitudes CFS - Actualización de Solicitud #"+solicitud+" - "+titulo);
                setCuerpo("Se sctualizó la solicitud #"+solicitud+" en el portal de solicitudes CFS \n"
                    + "Solicitud  #"+solicitud +" - "+titulo+"\n"
                    + "Asignado A : "+receiver1 +"\n"
                    + "Solicitante: "+receiver2 +"\n"
                    + "Saludos");
                break;
            case 2:
                setAsunto("Solicitudes CFS - Solicitud Cerrada #"+solicitud+" - "+titulo);
                setCuerpo("Se cerró la solicitud #"+solicitud+" en el portal de solicitudes CFS \n"
                    + "Solicitud  #"+solicitud +" - "+titulo+"\n"
                    + "Asignado A : "+receiver1 +"\n"
                    + "Solicitante: "+receiver2 +"\n"
                    + "Saludos");
                break;
            case 3:
                setAsunto("Solicitudes CFS - Nuevo comentario | Solicitud #"+solicitud+" - "+titulo);
                setCuerpo("Se creó un nuevo comentario en el portal de solicitudes CFS relacionado a la solicitud #"+solicitud+"\n"
                    + "Solicitud  #"+solicitud +" - "+titulo+"\n"
                    + "Asignado A : "+receiver1 +"\n"
                    + "Solicitante: "+receiver2 +"\n"
                    + "Saludos");
                break;
        }
        
        Email email = EmailBuilder.startingBlank()
            .from("From","admin@cfscr.com")
            .to("1 st Receiver", receiver1)
            .to("2 nd Receiver", receiver2)
            .withSubject(asunto)
            .withPlainText(cuerpo)
            .buildEmail();
        
        Mailer mailer = (Mailer) MailerBuilder
            .withSMTPServer("smtp.office365.com", 587, "admin@cfscr.com", "Dac97464")
            .withTransportStrategy(TransportStrategy.SMTP)
            .buildMailer();
        
        mailer.sendMail(email);
    }
}
