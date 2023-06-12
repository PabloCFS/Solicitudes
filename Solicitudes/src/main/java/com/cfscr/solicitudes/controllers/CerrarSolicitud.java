/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.entities.EstadoSolicitud;
import com.cfscr.solicitudes.entities.Mensaje;
import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.logic.Fechas;
import com.cfscr.solicitudes.service.ServiceListasImpl;
import com.cfscr.solicitudes.service.ServiceMensajeImpl;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class CerrarSolicitud extends HttpServlet {

    ServiceListasImpl servListasImpl = new ServiceListasImpl();
    ServiceMensajeImpl servMensajeImpl = new ServiceMensajeImpl();
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    ServiceSolicitudImpl servSolicituImpl = new ServiceSolicitudImpl();
    
    public CerrarSolicitud(){
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        String initial = config.getInitParameter("initial");
        
        servListasImpl = new ServiceListasImpl();
        servMensajeImpl = new ServiceMensajeImpl();
        servUsuarioImpl = new ServiceUsuarioImpl();
        servSolicituImpl = new ServiceSolicitudImpl();
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
        HttpSession session = request.getSession();
        
        Solicitud solicitud;
        Fechas fecha = new Fechas();
                
        String tipoSolicitud = "";
        String estadoSolicitud = "";
        
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<TipoSolicitud> tiposSolicitud = new ArrayList<>();
        ArrayList<EstadoSolicitud> estadosSolicitud = new ArrayList<>();
        
        Date fechaActualiza = stringToDate(fecha.fechaActual());
        
        //Captura datos
        int us = Integer.parseInt(request.getParameter("usuario"));
        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        String tipoLista = request.getParameter("tipoList");
        
        int list = Integer.parseInt(tipoLista);
    
        //Cerrar solicitud
        solicitud = servSolicituImpl.consultar(idSolicitud);
        solicitud.setEstado(2);
        solicitud.setFechaModificacion(fechaActualiza);
        servSolicituImpl.actualizar(solicitud);
        
        //Consultar datos de la solicitud | Mensajes
        solicitud = servSolicituImpl.consultar(idSolicitud);
        mensajes = servMensajeImpl.listarMensajes(mensajes, idSolicitud);
        usuarios = servUsuarioImpl.listar(usuarios);
        
        tiposSolicitud = servListasImpl.listarTipoSolicitud(tiposSolicitud);
        estadosSolicitud = servListasImpl.listarEstado(estadosSolicitud);
        
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
        
        //Enviar parametros
        session = request.getSession(true);
        
        request.setAttribute("us", us);
        session.setAttribute("us", us);
        
        request.setAttribute("list", list);
        session.setAttribute("list", list);
        
        request.setAttribute("tipoSolicitud", tipoSolicitud);
        session.setAttribute("tipoSolicitud", tipoSolicitud);
        
        request.setAttribute("estadoSolicitud", estadoSolicitud);
        session.setAttribute("estadoSolicitud", estadoSolicitud);
        
        request.setAttribute("mensajes", mensajes);
        session.setAttribute("mensajes", mensajes);
        
        request.setAttribute("usuarios", usuarios);
        session.setAttribute("usuarios", usuarios);
        
        request.setAttribute("solicitud", solicitud);
        session.setAttribute("solicitud", solicitud);
        
        request.getRequestDispatcher("VerSolicitud.jsp").forward(request, response); 
    }
    
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
