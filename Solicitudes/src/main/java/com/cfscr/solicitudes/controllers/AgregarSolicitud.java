/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import com.cfscr.solicitudes.entities.EstadoSolicitud;

import com.cfscr.solicitudes.service.ServiceListasImpl;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;

import com.cfscr.solicitudes.logic.Fechas;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class AgregarSolicitud extends HttpServlet {

    ServiceListasImpl servListasImpl = new ServiceListasImpl();
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    ServiceSolicitudImpl servSolicitudImpl = new ServiceSolicitudImpl();
    
    public AgregarSolicitud(){
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        String initial = config.getInitParameter("initial");
        
        servListasImpl = new ServiceListasImpl();
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
        System.out.println("Servlet AgregarSolicitud -> Declaracion de variables");
        
        HttpSession session = request.getSession();
       
        int idSolicitud = 0;
        Fechas fecha = new Fechas();
        Solicitud solicitud = new Solicitud();
        
        ArrayList<Usuario> listUsuario = new ArrayList<>();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        ArrayList<Solicitud> listSolicitud = new ArrayList<>();
        ArrayList<TipoSolicitud> listTipoSolicitud = new ArrayList<>();
        ArrayList<EstadoSolicitud> listEstSolicitud = new ArrayList<>();
        
        solicitudes = servSolicitudImpl.listar(solicitudes, "0", 0);
        
        //Asignar id a la solicitud
        System.out.println("Servlet AgregarSolicitud -> Asignar id a la solicitud");
        
        for(int i=0; i<solicitudes.size(); i++){
            idSolicitud = solicitudes.get(i).getId() + 1;
        }
       
        //Captura datos de Solicitud
        System.out.println("Servlet AgregarSolicitud -> Captura datos de la solicitud");
        
        String titulo = request.getParameter("tituloSolicitud");
        int tipo = Integer.parseInt(request.getParameter("TipoSolicitud"));        
        int us = Integer.parseInt(request.getParameter("userid"));
        String descripcion = request.getParameter("Descripcion");
        int idEstado = 1;
        int propietario = Integer.parseInt(request.getParameter("AsignarA"));
        
        Date fechaCreacion = stringToDate(fecha.fechaActual());
        Date fechaMod = stringToDate(fecha.fechaActual());
        
        String tipoLista = request.getParameter("listar");
        int list = Integer.parseInt(tipoLista);
        
        //Insertar Solicitud
        System.out.println("Servlet AgregarSolicitud -> Insertar Solicitud");
        
        solicitud = new Solicitud(idSolicitud,titulo,tipo,us,descripcion,idEstado,propietario,fechaCreacion,fechaMod);
        servSolicitudImpl.insertar(solicitud);
        
        //Consultar datos
        System.out.println("Servlet AgregarSolicitud -> Consultar datos DB");
        //0 - Listar Todas solicitudes
        //1- Listar todos relacionados un usuario
        //2- Listar abiertos realacionados a un usuario
        //3- Listar cerrados relacionados a un usuario
        listSolicitud = servSolicitudImpl.listar(listSolicitud, tipoLista, us);
        
        listUsuario = servUsuarioImpl.listar(listUsuario);
        listEstSolicitud = servListasImpl.listarEstado(listEstSolicitud);
        listTipoSolicitud = servListasImpl.listarTipoSolicitud(listTipoSolicitud);
        
        //Enviar parametros
        System.out.println("Servlet AgregarSolicitud -> Enviar parametros");
        
        session = request.getSession(true);
        
        request.setAttribute("userid", us);
        request.setAttribute("listar", list);
        request.setAttribute("listUsuario", listUsuario);
        request.setAttribute("listSolicitud", listSolicitud);
        request.setAttribute("listEstSolicitud", listEstSolicitud);
        request.setAttribute("listTipoSolicitud", listTipoSolicitud);
        
        session.setAttribute("userid", us);
        session.setAttribute("listar", list);
        session.setAttribute("listUsuario", listUsuario);
        session.setAttribute("listSolicitud", listSolicitud);
        session.setAttribute("listEstSolicitud", listEstSolicitud);
        session.setAttribute("listTipoSolicitud", listTipoSolicitud);
        
        request.getRequestDispatcher("VerSolicitudes.jsp").forward(request, response);
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
