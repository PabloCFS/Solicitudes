/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.EstadoSolicitud;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class DAOEstadoSolicitud extends ConexionDB{
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
    
    /**LISTAR ESTADO DE SOLICITUDES**/
    public ArrayList<EstadoSolicitud> listar(ArrayList<EstadoSolicitud> pLista){
        String SQL_LISTAR = "SP_LISTAR_ESTADO_SOLICITUD";
        CallableStatement csta;
        
        try{
            csta = cn.prepareCall(SQL_LISTAR);
            rs = csta.executeQuery();
            
            while(rs.next()){
                EstadoSolicitud est = new EstadoSolicitud(rs.getInt("ID_ESTADO"),rs.getString("NOMBRE_ESTADO"));
                
                pLista.add(est);
            }
            return pLista;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}