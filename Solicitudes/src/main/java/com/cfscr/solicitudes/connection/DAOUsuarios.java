/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.Usuario;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class DAOUsuarios extends ConexionDB{
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
    
    /**INSERTAR USUARIO**/
    public void insertar(Usuario pUsuario){
        String SQL_INSERTAR = "SP_INSERTAR_USUARIO ?,?,?,?,?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_INSERTAR);
            
            csta.setInt(1,pUsuario.getId());
            csta.setString(2,pUsuario.getNombre());
            csta.setInt(3,pUsuario.getRol());
            csta.setString(4,pUsuario.getPassword());
            csta.setString(5, pUsuario.getEmail());
            
            rs = csta.executeQuery();
        } catch(SQLException ex){
            System.out.println("DAOUsuarios - insertar - " + ex.toString());
        }
    }
    
    /**ELIMINAR USUARIO**/
    public boolean eliminar(int pId){
        String SQL_ELIMINAR = "SP_ELIMINAR_USUARIO ?";
        try{
            CallableStatement csta = cn.prepareCall(SQL_ELIMINAR);
            csta.setInt(1,pId);
            int n = csta.executeUpdate();
            
            if(n > 0){
                return true;
            }
            
        } catch(SQLException ex){
            System.out.println("DAOUsuarios - eliminar - " + ex.toString());
            return false;
        }
        return false;
    }
    
    /*LISTAR USUARIO*/
    public ArrayList<Usuario> listar(ArrayList<Usuario> pUsuario){
        String SQL_LISTAR = "SP_LISTAR_USUARIOS";
        CallableStatement csta;
        
        try{
            csta = cn.prepareCall(SQL_LISTAR);
            rs = csta.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("ID_USUARIO"),rs.getString("NOMBRE"),rs.getInt("ID_ROL"),rs.getString("CONTRASENIA"),rs.getString("CORREO"));
                
                pUsuario.add(usuario);
            }
            return pUsuario;
        } catch(SQLException ex){
            System.out.println("DAOUsuarios - listar - " + ex.toString());
            return null;
        }
    }
    
    /*OBTENER USUARIO*/
    public Usuario consultar(int pId){
        Usuario usuario = new Usuario();
        String SQL_CONSULTAR = "SP_CONSULTAR_USUARIO ?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_CONSULTAR);
            csta.setInt(1, pId);
            rs = csta.executeQuery();
            
            if(rs.next()){
                usuario = new Usuario(rs.getInt("ID_USUARIO"),rs.getString("NOMBRE"),rs.getInt("ID_ROL"),rs.getString("CONTRASENIA"),rs.getString("CORREO"));
            }
            return usuario;
        } catch(SQLException ex){
            System.out.println("DAOUsuarios - consultar - " + ex.toString());
            return usuario;
        }
    }
    
    /*MODIFICAR USUARIO*/
    public boolean actualizar(Usuario pUsuario){
        String SQL_MODIFICAR = "SP_ACUTALIZAR_USUARIO ?,?,?,?,?";
        try{
            CallableStatement csta = cn.prepareCall(SQL_MODIFICAR);
            csta.setInt(1, pUsuario.getId());
            csta.setString(2, pUsuario.getNombre());
            csta.setInt(3, pUsuario.getRol());
            csta.setString(4, pUsuario.getPassword());
            csta.setString(5,pUsuario.getEmail());
            
            csta.executeQuery();
            
            return true;
        } catch(SQLException ex){
            System.out.println("DAOUsuarios - actualizar - " + ex.toString());
            return false;
        }
    }
    
    /*CONSULTAR UN USUARIO LOGIN*/
    public Usuario login(int pId, String pContrasenia){
        Usuario miUsuario = null;
        
        try{
            CallableStatement csta = cn.prepareCall("SP_CONSULTAR_USUARIO_LOGIN ?,?");
            
            csta.setInt(1, pId);
            csta.setString(2, pContrasenia);
            rs = csta.executeQuery();
            
            if(rs.next()){
                miUsuario = new Usuario(rs.getInt("ID_USUARIO"),rs.getString("NOMBRE"),rs.getInt("ID_ROL"),rs.getString("CONTRASENIA"),rs.getString("CORREO"));
            }
            
        } catch(SQLException ex){
            System.out.println("DAOUsuarios - login - " + ex.toString());
            return miUsuario;
        }
        return miUsuario;
    }
}