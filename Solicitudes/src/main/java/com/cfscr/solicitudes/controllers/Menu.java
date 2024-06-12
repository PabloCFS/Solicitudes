/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.controllers;

import com.cfscr.solicitudes.service.ServiceUsuarioImpl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 *
 * @author pablo.elizondo
 */
public class Menu extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    ServiceUsuarioImpl servUsuarioImpl = new ServiceUsuarioImpl();
    
    public Menu(){
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        String initial = config.getInitParameter("initial");
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        //Declaracion de variables
        int userid;
        String usuario;
        String contrasenia = "";
        String tipoLlamado;
        
        //Consulta de datos
        tipoLlamado = (request.getParameter("tipoLlamado"));
        usuario = (request.getParameter("userid"));
        userid = Integer.parseInt(usuario);
        
        switch(tipoLlamado){
            case "Login":
                contrasenia = (request.getParameter("password"));
                
                if(evaluarUsuario(userid, contrasenia) == 0){
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            break;
        }
        
        //Envio de datos a JSP's
        session = request.getSession(true);
        
        request.setAttribute("userid", userid);
        session.setAttribute("userid", userid);
           
        request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
    }
    
    /**EVALUA SI EL USUARIO EXISTE EN LA DB**/
    private int evaluarUsuario(int userid, String pass){
        if(servUsuarioImpl.login(userid, pass) == null){
            System.out.println("Servlet Login -> Credenciales no encontradas");
            userid = 0;
        }
        if((servUsuarioImpl.login(userid, pass).getId() == userid) && 
           (servUsuarioImpl.login(userid, pass)).getPassword().equals(pass)){
            System.out.println("Servlet Login -> Credenciales correctas");
            userid = servUsuarioImpl.login(userid, pass).getId();
        }
        return userid;
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