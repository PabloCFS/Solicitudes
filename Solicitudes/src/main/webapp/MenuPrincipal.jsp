<%-- 
    Document   : MenuPrincipal
    Created on : 18 may. 2023, 09:57:59
    Author     : pablo.elizondo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1 maxium-scale=1.0, minium-scale=1.0">
        
        <%@include file = "Templates/Bootstrap.jsp"%>
        
        <link rel="stylesheet" type="text/css" href="Styles/cssMenuPrincipal.css">
        <link rel="icon" href="Sources/Images/logo_cfs-color.png">
        <title>CFS Solicitudes</title>
    </head>
    
    <body class="body-Menu">
        <%@include file="Templates/Header.jsp"%>
        
        <%int valor = (Integer) session.getAttribute("id_usuario");%>
        <!-- INICIO CUERPO DE LA PAGINA-->
        <section class="section-MenuPrincipal">
            <container class="container-MenuPrincial">
                <div class="MainDiv-MenuPrincipal">
                    <form class="form-Usuarios" action="AdministrarUsuarios" method="post" name="myForm">
                        <input type="submit" name="AdmUsuarios" value="Administración de usuarios" class="btn-lg button-form">
                    </form>

                    <form class="form-Solicitudes" action="AdmSolicitudes" method="post" name="myForm">
                        <input type="submit" name="AdmSolicitudes" value="Administración de Solicitudes" class="btn-lg button-form">
                    </form>
                                        
                    <form class="form-VerSolicitudes" action="SolicitudesUsuario" method="post" name="myForm">
                        <input type="hidden" id="idUs" name="idUs" value=<%=valor%>>
                        <input type="hidden" id="listarTodo" name="listar" value="1">
                        <input type="submit" name="MisSolicitudes" value="Mis Solicitudes" class="btn-lg button-form" id="id-formFinal">
                    </form>
                    
                </div>
            </container>
        </section>
        <!-- FIN CUERPO DE LA PAGINA-->
        
        <%@include file="Templates/Footer.jsp"%>
    </body>
</html>