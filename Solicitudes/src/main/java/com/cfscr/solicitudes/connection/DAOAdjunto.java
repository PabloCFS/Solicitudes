/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.Adjunto;
import java.nio.charset.StandardCharsets;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

import java.util.ArrayList;
/**
 *
 * @author pablo.elizondo
 */
public class DAOAdjunto extends ConexionDB{
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
    
    /*INSERTAR ADJUNTO*/
    public void insertar(Adjunto pAdjunto){
        String SQL_INSERTAR = "SP_INSERTAR_ADJUNTO ?,?,?,?,?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_INSERTAR);
            
            csta.setString(1, pAdjunto.getNombreArchivo());
            csta.setBytes(2,pAdjunto.getArchivo());
            csta.setString(3, pAdjunto.getExtension());
            csta.setInt(4, pAdjunto.getIdSolicitud());
            csta.setInt(5, pAdjunto.getIdMensaje());
            
            rs = csta.executeQuery();
        } catch(SQLException ex){
            System.out.println("DAOAdjunto - insertar - " + ex.toString());
        }   
    }    

    /*ELIMINAR ADJUNTO*/
    public boolean eliminar(int pIdAdjunto){
        String SQL_ELIMINAR = "SP_ELIMINAR_ADJUNTO ?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_ELIMINAR);
            csta.setInt(1, pIdAdjunto);
            int n = csta.executeUpdate();
            
            if(n > 0){
                return true;
            }
        } catch(SQLException ex){
            System.out.println("DAOSolicitud - eliminar - " + ex.toString());
            return false;
        }
        return false;
    }
    
    /*LISTAR ADJUNTOS PERTENECIENTES A SOLICITUD*/
    public ArrayList<Adjunto> listarAdjuntos(ArrayList<Adjunto> pAdjuntos, int pIdSolicitud){
        String SQL_LISTAR = "SP_CONSULTAR_ADJUNTO_SOLICITUD ?";
        CallableStatement csta;
        
        try{
            csta = cn.prepareCall(SQL_LISTAR);
            csta.setInt(1, pIdSolicitud);
            rs = csta.executeQuery();
            
            while(rs.next()){
                
                //CONVERSION FUNCIONA PARA BYTES TO STRING
                byte[] bytes = rs.getBytes("ARCHIVO");
                String doc = new String(bytes, StandardCharsets.UTF_8);
                
                Adjunto miAdjunto = new Adjunto(rs.getInt("ID_ARCHIVO"),rs.getString("NOMBRE_ARCHIVO"),/*rs.getString("ARCHIVO")*/bytes,rs.getString("EXTENSION"),rs.getInt("ID_SOLICITUD"),rs.getInt("ID_MENSAJE"));
                pAdjuntos.add(miAdjunto);
            }
            return pAdjuntos;
            
        } catch(SQLException ex){
            System.out.println("DAOAdjunto - listar - " + ex.toString());
            return null;
        }
    }
}