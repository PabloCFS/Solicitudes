<%-- 
    Document   : Login
    Created on : 18 may. 2023, 09:57:02
    Author     : pablo.elizondo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0m maxium-scale=1.0, minium-scale=1.0">
    
        <%@include file = "Templates/Bootstrap.jsp"%>
        <link rel="stylesheet" type="text/css" href="Styles/cssLogin.css">
        <link rel="icon" href="Sources/Images/logo_cfs-color.png">
        
        <title>CFS Solicitudes</title>
    </head>
    
    <body class="body">
        <section id="Main-Section-Login">
            <container class="container">
                <div class="div-AjustadosA-contenido">
                    <div class="divs-login">
                        <img class="logoCFS" src="Sources/Images/logo_cfs-color.png"/>
                    </div>
                    
                    <h1 class="h1-inicio">Inicio de Sesi&oacute;n</h1>
                    
                    <div class="divs-login">
                        <form class="formulario-Login" action="Login" method="post" name="myForm">
                            
                            <label class="label">Usuario</label>
                            <input type="text" class="form-control" required="true" oninvalid="this.setCustomValidity('Ingrese un usuario v&aacute;lido')"
                                   oninput="setCustomValidity('')" name="username"/>
                            
                            <label class="label">Contrase&ntilde;a</label>
                            <input type="password" class="form-control" required="true" oninvalid="this.setCustomValidity('Ingresa una clave correcta')"
                                   oninput="setCustomValidity('')" name="password" aria-describebody="passHelp"/>
                            
                            <input type="submit" name="Acceder" value="Acceder" class="boton-submit"/>
                        </form>
                    </div>
                </div>
            </container>
        </section>
        
        <section id="Footer-Section-Login">
            <h1 class="h1-Footer">admin@cfscr.com</h1>
        </section>
        
    </body>
</html>
