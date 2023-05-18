/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.entities.Usuario;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public interface ServiceUsuario {
    //INSERTAR USUARIO
    void insertar(Usuario pUsuario);
    
    //ELIMINAR USUARIO
    boolean eliminar(int pId);
    
    //LISTAR USUARIOS
    ArrayList<Usuario> listar(ArrayList<Usuario> pUsuario);
    
    //CONSULTAR USUARIO
    Usuario consultar(int pId);
    
    //ACTUALIZAR USUARIO
    boolean actualizar(Usuario pUsuario);
    
    //LOGEAR USUARIO
    Usuario login(int pId, String pContrasenia);
}