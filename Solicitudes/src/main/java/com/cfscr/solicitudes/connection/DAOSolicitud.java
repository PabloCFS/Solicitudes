
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cfscr.solicitudes.connection;

import com.cfscr.solicitudes.entities.Solicitud;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

import java.util.ArrayList;

/**
 *
 * @author pablo.elizondo
 */
public class DAOSolicitud extends ConexionDB{
    private ResultSet rs;
    private final Connection cn = ConexionDB.getConnection();
    
    /**INSERTAR SOLICITUD**/
    public void insertar(Solicitud pSolicitud){
        String SQL_INSERTAR = "SP_INSERTAR_SOLICITUD ?,?,?,?,?,?,?,?,?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_INSERTAR);
            
            csta.setInt(1,pSolicitud.getId());
            csta.setString(2,pSolicitud.getTitulo());
            csta.setInt(3,pSolicitud.getIdTipo());
            csta.setInt(4,pSolicitud.getIdSolicitante());
            csta.setString(5,pSolicitud.getDescripcion());
            csta.setInt(6,pSolicitud.getEstado());
            csta.setInt(7,pSolicitud.getIdPropietario());
            csta.setDate(8,pSolicitud.getFechaCreacion());
            csta.setDate(9,pSolicitud.getFechaModificacion());
        
            rs = csta.executeQuery();
        } catch(SQLException ex){
            System.out.println("DAOSolicitud - insertar - " + ex.toString());
        }
    }
    
    /**ELIMINAR SOLICITUD**/
    public boolean eliminar(int pIdSolicitud){
        String SQL_ELIMINAR = "SP_ELIMINAR_SOLICITUD ?";
        try{
            CallableStatement csta = cn.prepareCall(SQL_ELIMINAR);
            csta.setInt(1,pIdSolicitud);
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
    
    /**LISTAR SOLICITUD**/
    public ArrayList<Solicitud> listar(ArrayList<Solicitud> pSolicitud, String pTipoLista, int pIdUsuario){
        String SQL_LISTAR = "SP_LISTAR_SOLICITUDES";
        CallableStatement csta;
       
        try{
            switch(pTipoLista){
                case "0":      
                    csta = cn.prepareCall(SQL_LISTAR);
                    rs = csta.executeQuery();
                    break;
                case "1":
                    csta = cn.prepareCall(SQL_LISTAR + "_USUARIO ?");
                    csta.setInt(1, pIdUsuario);
                    rs = csta.executeQuery();
                    break;
                case "2":
                    csta = cn.prepareCall(SQL_LISTAR + "_ABIERTAS_USUARIO ?");
                    csta.setInt(1, pIdUsuario);
                    rs = csta.executeQuery();
                    break;
                case "3":
                    csta = cn.prepareCall(SQL_LISTAR + "_CERRADAS_USUARIO ?");
                    csta.setInt(1, pIdUsuario);
                    rs = csta.executeQuery();
                    break;
                case "4":
                    csta = cn.prepareCall(SQL_LISTAR + "_SOLICITANTE ?");
                    csta.setInt(1, pIdUsuario);
                    rs = csta.executeQuery();
                    break;
                case "5":
                    csta = cn.prepareCall(SQL_LISTAR + "_ABIERTAS_SOLICITANTE ?");
                    csta.setInt(1, pIdUsuario);
                    rs = csta.executeQuery();
                    break;
                case "6":
                    csta = cn.prepareCall(SQL_LISTAR + "_CERRADAS_SOLICITANTE ?");
                    csta.setInt(1, pIdUsuario);
                    rs = csta.executeQuery();
                    break;
            }
            
            while(rs.next()){
                Solicitud solicitud = new Solicitud(rs.getInt("ID_SOLICITUD"),rs.getString("TITULO"),rs.getInt("TIPO_SOLICITUD"),rs.getInt("ID_SOLICITANTE"),rs.getString("DESCRIPCION"),rs.getInt("ID_ESTADO"),rs.getInt("ID_PROPIETARIO"),rs.getDate("FECHA_CREACION"),rs.getDate("FECHA_MODIFICACION"));
                pSolicitud.add(solicitud);
            }
            
            return pSolicitud;
        } catch(SQLException ex){
            System.out.println("DAOSolicitud - listar - " + ex.toString());
            return null;
        }
    }
    
    /**OBTENER SOLICITUD**/
    public Solicitud consultar(int pId){
        Solicitud miSolicitud = new Solicitud();
        String SQL_CONSULTAR = "SP_CONSULTAR_SOLICITUD ?";
        
        try{
            CallableStatement csta = cn.prepareCall(SQL_CONSULTAR);
            csta.setInt(1, pId);
            rs = csta.executeQuery();
            
            if(rs.next()){
                miSolicitud = new Solicitud(rs.getInt("ID_SOLICITUD"),rs.getString("TITULO"),rs.getInt("TIPO_SOLICITUD"),rs.getInt("ID_SOLICITANTE"),rs.getString("DESCRIPCION"),rs.getInt("ID_ESTADO"),rs.getInt("ID_PROPIETARIO"),rs.getDate("FECHA_CREACION"),rs.getDate("FECHA_MODIFICACION"));
            }
            return miSolicitud;
        } catch(SQLException ex){
            System.out.println("DAOSolicitud - consultar - " + ex.toString());
            return miSolicitud;
        }
    }
    
    /**MODIFICAR SOLICITUD**/
    public boolean actualizar(Solicitud pSolicitud){
        String SQL_MODIFICAR = "SP_ACTUALIZAR_SOLICITUD ?,?,?,?,?,?,?,?,?";
        try{
            CallableStatement csta = cn.prepareCall(SQL_MODIFICAR);
            csta.setInt(1, pSolicitud.getId());
            csta.setString(2, pSolicitud.getTitulo());
            csta.setInt(3, pSolicitud.getIdTipo());
            csta.setInt(4, pSolicitud.getIdSolicitante());
            csta.setString(5, pSolicitud.getDescripcion());
            csta.setInt(6, pSolicitud.getEstado());
            csta.setInt(7, pSolicitud.getIdPropietario());
            csta.setDate(8, pSolicitud.getFechaCreacion());
            csta.setDate(9, pSolicitud.getFechaModificacion());
            
            csta.executeQuery();
            
            return true;
        } catch(SQLException ex){
            System.out.println("DAOSolicitud - actualizar - " + ex.toString());
            return false;
        }
    }
}
