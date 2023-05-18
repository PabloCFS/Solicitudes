/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.service;

import com.cfscr.solicitudes.connection.DAOUsuarios;
import com.cfscr.solicitudes.entities.Usuario;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class ServiceUsuarioImpl implements ServiceUsuarioExt{
    
    private final DAOUsuarios daoUsuario = new DAOUsuarios();
    
    //INSERTAR USUARIO
    @Override
    public void insertar(Usuario pUsuario) {
        daoUsuario.insertar(pUsuario);
    }

    //ELIMINAR USUARIO
    @Override
    public boolean eliminar(int pId) {
        return daoUsuario.eliminar(pId);
    }

    //LISTAR USUARIO
    @Override
    public ArrayList<Usuario> listar(ArrayList<Usuario> pUsuario) {
        return daoUsuario.listar(pUsuario);
    }

    //CONSULTAR USUARIO
    @Override
    public Usuario consultar(int pId) {
        return daoUsuario.consultar(pId);
    }

    //ACTUALIZAR USUARIO
    @Override
    public boolean actualizar(Usuario pUsuario) {
        return daoUsuario.actualizar(pUsuario);
    }
    
    //LOGEAR USUARIO
    @Override
    public Usuario login(int pId, String pContrasenia){
        return daoUsuario.login(pId, pContrasenia);
    }
}