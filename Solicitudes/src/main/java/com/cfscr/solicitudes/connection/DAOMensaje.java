/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.Mensaje;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class DAOMensaje extends ConexionDB{
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
    
    /*INSERTAR MENSAJE*/
    public void insertar(Mensaje pMensaje){
        String SQL_INSERTAR = "SP_INSERTAR_MENSAJE ?,?,?,?,?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_INSERTAR);
            
            csta.setInt(1,pMensaje.getId());
            csta.setString(2,pMensaje.getDescripcion());
            csta.setInt(3,pMensaje.getIdSolicitud());
            csta.setInt(4,pMensaje.getIdUsuarioComenta());
            csta.setDate(5,pMensaje.getFechaCreacion());
            
            rs = csta.executeQuery();
        } catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    /*ELIMINAR MENSAJE*/
    public boolean eliminar(int pId){
        String SQL_ELIMINAR = "SP_ELIMINAR_MENSAJE ?";
        try{
            CallableStatement csta = cn.prepareCall(SQL_ELIMINAR);
            csta.setInt(1,pId);
            int n = csta.executeUpdate();
            
            if(n > 0){
                return true;
            }
            
        } catch(SQLException ex){
            return false;
        }
        return false;
    }
    
    /*LISTAR MENSAJE*/
    public ArrayList<Mensaje> listar(ArrayList<Mensaje> pMensaje){
        String SQL_LISTAR = "SP_LISTAR_MENSAJES";
        CallableStatement csta;
        
        try{
            csta = cn.prepareCall(SQL_LISTAR);
            rs = csta.executeQuery();
            
            while(rs.next()){
                Mensaje mensaje = new Mensaje(rs.getInt("ID_MENSAJE"), rs.getString("DESCRIPCION"), rs.getInt("ID_SOLICITUD"), rs.getInt("ID_USUARIO_COMENTA"), rs.getDate("FECHA_CREACION"));
                
                pMensaje.add(mensaje);
            }
            return pMensaje;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    
    /*OBTENER MENSAJE*/
    public Mensaje consultar(int pId){
        Mensaje miMensaje = new Mensaje();
        String SQL_CONSULTAR = "SP_CONSULTAR_MENSAJE ?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_CONSULTAR);
            csta.setInt(1, pId);
            rs = csta.executeQuery();
            
            if(rs.next()){
                miMensaje = new Mensaje(rs.getInt("ID_MENSAJE"), rs.getString("DESCRIPCION"), rs.getInt("ID_SOLICITUD"), rs.getInt("ID_USUARIO_COMENTA"), rs.getDate("FECHA_CREACION"));
            }
            return miMensaje;
        } catch(SQLException ex){
            System.out.println(ex);
            return miMensaje;
        }
    }

    /*CONSULTAR MENSAJE*/
    public boolean actualizar(Mensaje pMensaje){
        String SQL_MODIFICAR = "SP_ACUTALIZAR_MENSAJE ?,?,?,?,?";
        try{
            CallableStatement csta = cn.prepareCall(SQL_MODIFICAR);
            csta.setInt(1, pMensaje.getId());
            csta.setString(2, pMensaje.getDescripcion());
            csta.setInt(3, pMensaje.getIdSolicitud());
            csta.setInt(4, pMensaje.getIdUsuarioComenta());
            csta.setDate(5, pMensaje.getFechaCreacion());
            
            csta.executeQuery();
            
            return true;
        } catch(SQLException ex){
            System.out.println(ex);
            return false;
        }
    }
}