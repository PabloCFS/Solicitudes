/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.entities.Mensaje;
import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import com.cfscr.solicitudes.entities.EstadoSolicitud;

import com.cfscr.solicitudes.service.ServiceListasImpl;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import com.cfscr.solicitudes.service.ServiceMensajeImpl;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class VerSolicitud extends HttpServlet {

    ServiceListasImpl servListasImpl = new ServiceListasImpl();
    ServiceMensajeImpl servMensajeImpl = new ServiceMensajeImpl();
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    ServiceSolicitudImpl servSolicitudImpl = new ServiceSolicitudImpl();
    
    public VerSolicitud(){
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
        System.out.println("Servlet VerSolicitud -> Declaracion de variables");
        
        HttpSession session = request.getSession();
        
        String tipoSolicitud = "";
        String estadoSolicitud = "";
        
        Solicitud solicitud = new Solicitud();
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<TipoSolicitud> tiposSolicitud = new ArrayList<>();
        ArrayList<EstadoSolicitud> estadosSolicitud = new ArrayList<>();
        
        //Captura datos
        System.out.println("Servlet VerSolicitud -> Captura datos");
        
        String tipoLista = request.getParameter("listar");
        int us = Integer.parseInt(request.getParameter("userid"));
        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        
        int list = Integer.parseInt(tipoLista);
        
        //Consultar datos de la solicitud | Mensajes
        System.out.println("Servlet VerSolicitud -> Consultar datos de la solicitud | Mensajes");
        
        usuarios = servUsuarioImpl.listar(usuarios);
        solicitud = servSolicitudImpl.consultar(idSolicitud);
        mensajes = servMensajeImpl.listarMensajes(mensajes, idSolicitud);
        
        estadosSolicitud = servListasImpl.listarEstado(estadosSolicitud);
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
        /**
         * 
         * 
         * ADJUNTOS
         * 
         * 
         **/
        
        //Enviar parametros
        System.out.println("Servlet VerSolicitud -> Enviar parametros");
        
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