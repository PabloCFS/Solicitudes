/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.adjunto.Adj;
import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.entities.Solicitud;
import com.cfscr.solicitudes.entities.TipoSolicitud;
import com.cfscr.solicitudes.entities.EstadoSolicitud;

import com.cfscr.solicitudes.service.ServiceFechaImpl;
import com.cfscr.solicitudes.service.ServiceCorreoImpl;
import com.cfscr.solicitudes.service.ServiceListasImpl;
import com.cfscr.solicitudes.service.ServiceAdjuntoImpl;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;
import com.cfscr.solicitudes.service.ServiceSolicitudImpl;

import jakarta.servlet.http.Part;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;

import java.io.File;
import java.io.IOException;

import java.sql.Date;

import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5,
    maxRequestSize = 1024 * 1024 * 5 * 5)
public class AgregarSolicitud extends HttpServlet {

    private static final long serialVersionUID = 1L;
            
    ServiceFechaImpl servFechaImpl = new ServiceFechaImpl();
    ServiceCorreoImpl servCorreoImpl = new ServiceCorreoImpl();
    ServiceListasImpl servListasImpl = new ServiceListasImpl();
    ServiceAdjuntoImpl servAdjuntoImpl = new ServiceAdjuntoImpl();
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    ServiceSolicitudImpl servSolicitudImpl = new ServiceSolicitudImpl();
    
    public AgregarSolicitud(){
        super();
    }
    
    /*
    DESCOMENTADO -> ERROR CON CARGA DE ARCHIVOS.
    @Override
    public void init(ServletConfig config) throws ServletException{
        String initial = config.getInitParameter("initial");
        
        
        servCorreoImpl = new ServiceCorreoImpl();
        servListasImpl = new ServiceListasImpl();
        servUsuarioImpl = new ServiceUsuarioImpl();
        servSolicitudImpl = new ServiceSolicitudImpl();
    }
    */
    
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

        ////////////////////////Declaracion de variables
        System.out.println("Servlet AgregarSolicitud -> Declaracion de variables");
        
        HttpSession session = request.getSession();
        
        int idSolicitud = 0;
        Solicitud solicitud = new Solicitud();
        
        ArrayList<Usuario> listUsuario = new ArrayList<>();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        ArrayList<Solicitud> listSolicitud = new ArrayList<>();
        ArrayList<TipoSolicitud> listTipoSolicitud = new ArrayList<>();
        ArrayList<EstadoSolicitud> listEstSolicitud = new ArrayList<>();
        
        solicitudes = servSolicitudImpl.listar(solicitudes, "0", 0);
        
        ////////////////////////Asignar id a la solicitud
        System.out.println("Servlet AgregarSolicitud -> Asignar id a la solicitud");
        
        for(int i=0; i<solicitudes.size(); i++){
            idSolicitud = solicitudes.get(i).getId() + 1;
        }
        
        ////////////////////////Captura datos de Solicitud
        System.out.println("Servlet AgregarSolicitud -> Captura datos de la solicitud");
        
        String titulo = request.getParameter("tituloSolicitud");
        int tipo = Integer.parseInt(request.getParameter("TipoSolicitud"));        
        int us = Integer.parseInt(request.getParameter("userid"));
        String descripcion = request.getParameter("Descripcion");
        int idEstado = 1;
        int propietario = Integer.parseInt(request.getParameter("AsignarA"));
        
        Date fechaCreacion = servFechaImpl.stringToDate(servFechaImpl.fechaActual());
        Date fechaMod = servFechaImpl.stringToDate(servFechaImpl.fechaActual());
        
        String tipoLista = request.getParameter("listar");
        int list = Integer.parseInt(tipoLista);
        
        String emailSolicitante = "";
        String emailPropietario = "";
        
        //Insertar Solicitud
        System.out.println("Servlet AgregarSolicitud -> Insertar Solicitud");
        
        solicitud = new Solicitud(idSolicitud,titulo,tipo,us,descripcion,idEstado,propietario,fechaCreacion,fechaMod);
        servSolicitudImpl.insertar(solicitud);
        
        ////////////////////////Capturar adjunto
        if(request.getPart("subeArchivo").getSize() > 0){
            String uploadPath = getServletContext().getRealPath("Archivos") + File.separator;
            File uploadDir = new File(uploadPath);
            
            if(!uploadDir.exists()) uploadDir.mkdir();
            
            String na = "";
            for(Part part : request.getParts()){
                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            
                if(fileName != null){
                    na = fileName;
                }
            }
        
            System.out.println("\n na -> " + na);
            Adj miAdj = new Adj();
            miAdj.insertar(uploadPath, na, idSolicitud, 0);
        }
        
        //Envio de correos
        // 0) Nuevo Caso
        emailSolicitante = servUsuarioImpl.consultar(us).getEmail();
        emailPropietario = servUsuarioImpl.consultar(propietario).getEmail();
        servCorreoImpl.enviarCorreo(0, emailPropietario, emailSolicitante, idSolicitud, solicitud.getTitulo());
        
        //Consultar datos
        System.out.println("Servlet AgregarSolicitud -> Consultar datos DB");
        //0- Listar Todas solicitudes
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
