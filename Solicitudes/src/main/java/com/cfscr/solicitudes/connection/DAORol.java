/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.Rol;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class DAORol {
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
 
    /**LISTAR ROLES*/
    public ArrayList<Rol> listar(ArrayList<Rol> pLista){
        String SQL_LISTAR = "SP_LISTAR_ROLES";
        CallableStatement csta;
        
        try{
            csta = cn.prepareCall(SQL_LISTAR);
            rs = csta.executeQuery();
            
            while(rs.next()){
                Rol rol = new Rol(rs.getInt("ID_ROL"),rs.getString("NOMBRE_ROL"));
                
                pLista.add(rol);
            }
            return pLista;
        } catch(SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
