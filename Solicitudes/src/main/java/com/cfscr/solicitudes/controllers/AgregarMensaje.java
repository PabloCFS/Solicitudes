/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.entities.Mensaje;
import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import com.cfscr.solicitudes.entities.EstadoSolicitud;

import com.cfscr.solicitudes.service.ServiceListasImpl;
import com.cfscr.solicitudes.service.ServiceMensajeImpl;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;

import com.cfscr.solicitudes.logic.Fechas;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.io.IOException;

import java.sql.Date;
/**
 *
 * @author pablo.elizondo
 */
public class AgregarMensaje extends HttpServlet {
    
    ServiceListasImpl servListasImpl = new ServiceListasImpl();
    ServiceMensajeImpl servMensajeImpl = new ServiceMensajeImpl();
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    ServiceSolicitudImpl servSolicitudImpl = new ServiceSolicitudImpl();

    public AgregarMensaje(){
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        String initial = config.getInitParameter("initial");
        
        servListasImpl = new ServiceListasImpl();
        servMensajeImpl = new ServiceMensajeImpl();
        servUsuarioImpl = new ServiceUsuarioImpl();
        servSolicitudImpl = new ServiceSolicitudImpl();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declaracion de variables
        System.out.println("Servlet AgregarMensaje -> Declaracion de variables");
        
        HttpSession session = request.getSession();
        
        int idMensaje = 0;
        String tipoSolicitud = "";
        String estadoSolicitud = "";
        
        Solicitud solicitud;
        Fechas fecha = new Fechas();
        Mensaje miMensaje = new Mensaje();
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Mensaje> todosMensajes = new ArrayList<>();
        ArrayList<TipoSolicitud> tiposSolicitud = new ArrayList<>();
        ArrayList<EstadoSolicitud> estadosSolicitud = new ArrayList<>();
        
        //Captura datos de mensaje
        System.out.println("Servlet AgregarMensaje -> Captura datos de mensaje");
        
        todosMensajes = servMensajeImpl.listar(todosMensajes);
        for(int i=0; i<todosMensajes.size(); i++){
            idMensaje = todosMensajes.get(i).getId() + 1;
        }
       
        String mensaje = request.getParameter("mensaje");
        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        int us = Integer.parseInt(request.getParameter("userid"));
        
        Date fechaCreacion = stringToDate(fecha.fechaActual());
        
        String tipoLista = request.getParameter("listar");
        int list = Integer.parseInt(tipoLista);
        
        //Insertar Mensaje
        System.out.println("Servlet AgregarMensaje -> Insertar Mensaje");
        
        mensajes = servMensajeImpl.listarMensajes(mensajes, idSolicitud);
        miMensaje = new Mensaje(idMensaje,mensaje,idSolicitud,us,fechaCreacion);
        
        solicitud = servSolicitudImpl.consultar(idSolicitud);
        
        //Evaluar si los dos ultimos son repetidos || EstadoSolicitud Abierto = 1 || EstadoSolicitud Cerrado = 2
        if( ( (mensajes.isEmpty()) ) && (solicitud.getEstado() == 1) ){
            servMensajeImpl.insertar(miMensaje);
        }
        if( (!mensajes.isEmpty()) ){
           if( (
                (mensajes.get(mensajes.size()-1).getId() < idMensaje) &&
                (mensajes.get(mensajes.size()-1).getDescripcion().equals(mensaje)) &&
                (mensajes.get(mensajes.size()-1).getIdSolicitud() == idSolicitud) &&
                (mensajes.get(mensajes.size()-1).getIdUsuarioComenta() == us) &&
                (mensajes.get(mensajes.size()-1).getFechaCreacion().equals(fechaCreacion)) ) ||
                (solicitud.getEstado() == 2) ){
                    System.out.println("Servlet AgregarMensaje -> No Insertar");
            } else {
                servMensajeImpl.insertar(miMensaje);
            }
        }
        mensajes.clear();
        
        //Consultar datos
        System.out.println("Servlet AgregarMensaje -> Consultar datos");
        
        usuarios = servUsuarioImpl.listar(usuarios);
        
        estadosSolicitud = servListasImpl.listarEstado(estadosSolicitud);
        mensajes = servMensajeImpl.listarMensajes(mensajes, idSolicitud);
        tiposSolicitud = servListasImpl.listarTipoSolicitud(tiposSolicitud);
        
        for(int i=0; i<tiposSolicitud.size(); i++){
            if(tiposSolicitud.get(i).getId() == solicitud.getIdTipo()){
                tipoSolicitud = tiposSolicitud.get(i).getNombre();
            }
        }
        
        for(int i=0; i<estadosSolicitud.size(); i++){
            if(estadosSolicitud.get(i).getId() == solicitud.getEstado()){
                estadoSolicitud = estadosSolicitud.get(i).getNombre();
            }
        }
        
        //Actualizar Solicitud
        System.out.println("Servlet AgregarMensaje -> Actualizar Solicitud");
        
        solicitud.setFechaModificacion(fechaCreacion);
        servSolicitudImpl.actualizar(solicitud);
        solicitud = servSolicitudImpl.consultar(idSolicitud);
        
        //Enviar datos
        System.out.println("Servlet AgregarMensaje -> Enviar datos");
        session = request.getSession(true);
        
        request.setAttribute("userid", us);
        request.setAttribute("listar", list);
        request.setAttribute("mensajes", mensajes);
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("solicitud", solicitud);
        request.setAttribute("tipoSolicitud", tipoSolicitud);
        request.setAttribute("estadoSolicitud", estadoSolicitud);
        
        session.setAttribute("userid", us);
        session.setAttribute("listar", list);
        session.setAttribute("mensajes", mensajes);
        session.setAttribute("usuarios", usuarios);
        session.setAttribute("solicitud", solicitud);
        session.setAttribute("tipoSolicitud", tipoSolicitud);
        session.setAttribute("estadoSolicitud", estadoSolicitud);
        
        request.getRequestDispatcher("VerSolicitud.jsp").forward(request, response);
    }
    
    //Funcion para pasar de String a Date
    private Date stringToDate(String fecha){
        Date date = Date.valueOf(fecha);
        return date;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
