/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.logic.Fechas;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;
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
public class AgregarSolicitud extends HttpServlet {

    ServiceSolicitudImpl servSolicitudImpl = new ServiceSolicitudImpl();
    
    public AgregarSolicitud(){
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        String initial = config.getInitParameter("initial");
        
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");
        
        //Lista de manipulacion de listas
        ArrayList<Solicitud> listSolicitud = new ArrayList<>();
        listSolicitud = servSolicitudImpl.listar(listSolicitud, "0", 0);
        Fechas fecha = new Fechas();
        
        //Captura datos para almacenar solicitud
        int idSolicitud = 0;
        for(int i=0; i<listSolicitud.size(); i++){
            idSolicitud = listSolicitud.get(i).getId() + 1;
        }
       
        String titulo = request.getParameter("tituloSolicitud");
        int tipo = Integer.parseInt(request.getParameter("TipoSolicitud"));        
        int idUs = Integer.parseInt(request.getParameter("idUs"));
        String descripcion = request.getParameter("Descripcion");
        int idEstado = 1;
        int propietario = Integer.parseInt(request.getParameter("AsignarA"));
        
        /////////////////////////////
        String fechaMod = fecha.fechaActual();
        String fechaCreacion = fecha.fechaActual();
        /////////////////////////////
        
        String tipoLista = request.getParameter("tipoList");
        
        System.out.println("idSolicitud ->"+idSolicitud);
        System.out.println("titulo -> "+titulo);
        System.out.println("tipo -> "+tipo);
        System.out.println("idUs -> "+idUs);
        System.out.println("descripcion -> "+ descripcion);
        System.out.println("idEstado -> "+idEstado);
        System.out.println("propietario -> "+propietario);
        System.out.println("fechaCreacion -> "+fechaCreacion);
        System.out.println("fechaMod -> "+fechaMod);
        System.out.println("tipoLista -> "+tipoLista);
        
        session = request.getSession(true);
        System.out.println("Servlet agregar solicitud -> 94");
        
        request.setAttribute("us", idUs);
        session.setAttribute("us", idUs);
        
        /*
        request.setAttribute("list", tipoLista);
        session.setAttribute("list", tipoLista);
        */
        
        response.sendRedirect("SolicitudesUsuario");
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
