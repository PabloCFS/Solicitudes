/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.cfscr.solicitudes.entities.Usuario;
import com.cfscr.solicitudes.service.ServiceUsuarioImpl;

/**
 *
 * @author pablo.elizondo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServiceUsuarioImpl serv = new ServiceUsuarioImpl();
        //DAOUsuarios usu = new DAOUsuarios();
        Usuario usuario = serv.login(117270025, "117270025");
        
        System.out.println("Nombre -> " + usuario.getNombre());
        System.out.println("Cedula -> " + usuario.getId());
        
    }
    
}
