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
public interface ServiceUsuarioExt extends ServiceUsuario{
    //INSERTAR USUARIO
    @Override
    void insertar(Usuario pUsuario);
    
    //ELIMINAR USUARIO
    @Override
    boolean eliminar(int pId);
    
    //LISTAR USUARIOS
    @Override
    ArrayList<Usuario> listar(ArrayList<Usuario> pUsuario);
    
    //CONSULTAR USUARIO
    @Override
    Usuario consultar(int pId);
    
    //ACTUALIZAR USUARIO
    @Override
    boolean actualizar(Usuario pUsuario);

    //LOGEAR USUARIO
    @Override
    Usuario login(int pId, String pContrasenia);
}