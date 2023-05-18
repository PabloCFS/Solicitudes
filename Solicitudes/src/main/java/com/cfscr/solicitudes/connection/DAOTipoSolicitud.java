/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.TipoSolicitud;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class DAOTipoSolicitud {
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
    
    /**LISTAR TIPOS DE SOLICITUDES**/
    public ArrayList<TipoSolicitud> listar(ArrayList<TipoSolicitud> pLista){
        String SQL_LISTAR = "SP_LISTAR_TIPOS_SOLICITUD";
        CallableStatement csta;
        
        try{
            csta = cn.prepareCall(SQL_LISTAR);
            rs = csta.executeQuery();
            
            while(rs.next()){
                TipoSolicitud tipo = new TipoSolicitud(rs.getInt("ID_TIPO"),rs.getString("NOMBRE"));
                
                pLista.add(tipo);
            }
            return pLista;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
