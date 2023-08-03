/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 *
 * @author pablo.elizondo
 */
public class ConexionDB {
    
    public static Connection getConnection(){
        String ConexionUrl = "jdbc:sqlserver://PABLOELIZONDOPC:1433;"
                + "databaseName=DB_SOLICITUDES;"
                + "user=sa;"
                + "password=Hola1998;"
                + "loginTimeout=30;";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver OK");
            
            Connection con = DriverManager.getConnection(ConexionUrl);
            return con;
            
        } catch(SQLException ex) {
            System.out.println("Conexion DB 33 - "+ ex.toString());
            Logger.getLogger(ConexionUrl);
            
            return null;
        } catch(ClassNotFoundException e){
            System.out.println("ConexionDB 38" + e.toString());
        }
        return null;
    }
}