/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.entities.EstadoSolicitud;
import com.cfscr.solicitudes.entities.Rol;
import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.service.ServiceListasImpl;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import jakarta.servlet.ServletConfig;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class SolicitudesUsuario extends HttpServlet {
    
    ServiceSolicitudImpl servSolicitudImpl = new ServiceSolicitudImpl();
    ServiceListasImpl servlistasImpl = new ServiceListasImpl();
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    
    public SolicitudesUsuario(){
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException{
        String initial = config.getInitParameter("initial");
        servSolicitudImpl = new ServiceSolicitudImpl();
        servlistasImpl = new ServiceListasImpl();
        servUsuarioImpl = new ServiceUsuarioImpl();
        
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ArrayList<Solicitud> listSolicitud = new ArrayList<>();
        ArrayList<EstadoSolicitud> listEstSolicitud = new ArrayList<>();
        ArrayList<TipoSolicitud> listTipoSolicitud = new ArrayList<>();
        ArrayList<Usuario> listUsuario = new ArrayList<>();
        
        response.setContentType("text/html;charset=utf-8");
        
        String idUsuario = request.getParameter("idUs");
        String listar = request.getParameter("listar");
        
        int us = Integer.parseInt(idUsuario);
        int list = Integer.parseInt(listar);
        
        //0 - Listar Todas solicitudes
        //1- Listar todos relacionados un usuario
        //2- Listar abiertos realacionados a un usuario
        //3- Listar cerrados relacionados a un usuario
        listSolicitud = servSolicitudImpl.listar(listSolicitud, listar, us);
        
        listEstSolicitud = servlistasImpl.listarEstado(listEstSolicitud);
        listTipoSolicitud = servlistasImpl.listarTipoSolicitud(listTipoSolicitud);
        
        listUsuario = servUsuarioImpl.listar(listUsuario);
        
        session = request.getSession(true);
                
        request.setAttribute("us", us);
        session.setAttribute("us", us);
        
        request.setAttribute("list", list);
        session.setAttribute("list", list);
        
        request.setAttribute("listSolicitud", listSolicitud);
        session.setAttribute("listSolicitud", listSolicitud);
        
        request.setAttribute("listEstSolicitud", listEstSolicitud);
        session.setAttribute("listEstSolicitud", listEstSolicitud);
        
        request.setAttribute("listTipoSolicitud", listTipoSolicitud);
        session.setAttribute("listTipoSolicitud", listTipoSolicitud);
        
        request.setAttribute("listUsuario", listUsuario);
        session.setAttribute("listUsuario", listUsuario);
        
        request.getRequestDispatcher("VerSolicitudes.jsp").forward(request, response);
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
