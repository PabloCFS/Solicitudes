<%-- 
    Document   : VerSolicitud
    Created on : 23 may. 2023, 15:38:45
    Author     : pablo.elizondo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewmport" content="width=device-width, user-scalable=no, initial-scale=1.0m maxium-scale=1.0, minium-scale=1.0">
        
        <%@include file = "Templates/Bootstrap.jsp"%>
        
        <link rel="stylesheet" type="text/css" href="Styles/cssVerSolicitud.css">
        <link rel="icon" href="Sources/Images/logo_cfs-color.png">
        
        <title>CFS Solicitudes</title>
    </head>
    
    <body class="body-menu">
        <!-- VARIABLES -->
        <%@page import="com.cfscr.solicitudes.entities.Mensaje" %>
        <%@page import="com.cfscr.solicitudes.entities.Usuario" %>
        <%@page import="com.cfscr.solicitudes.entities.Solicitud" %>
        <%@page import="java.util.ArrayList" %>
        
        <jsp:useBean id="mensajes" scope="request" type="java.util.ArrayList"/>
        <jsp:useBean id="usuarios" scope="request" type="java.util.ArrayList"/>
        
        <%ArrayList<Mensaje> mensajesHtml = mensajes;%>
        <%ArrayList<Usuario> usuariosHtml = usuarios;%>
        
        <%int us = (Integer) session.getAttribute("us");%>
        <%int list = (Integer) session.getAttribute("list");%>
        <%String tipoSolicitud = (String) session.getAttribute("tipoSolicitud");%>
        <%String estadoSolicitud = (String) session.getAttribute("estadoSolicitud");%>
        
        <%Solicitud miSolicitud = (Solicitud) session.getAttribute("solicitud");%>
        
        <%@include file="Templates/Header.jsp"%>
        
        <!-- INICIO CUERPO DE LA PAGINA -->
        <section>
            <container>
                <div>
                    <table class="table table-fixed table-responsive-lg table-hover">
                        <thead class="bg-secondary text-white">
                            <tr>
                                <th class="text-center col-xs-2" colspan="4"><%=miSolicitud.getTitulo()%></th>
                            </tr>
                            <tr>
                                <th class="text-center col-xs-2" colspan="4"><%=miSolicitud.getId()%></th>
                            </tr>
                            <tr>
                                <th class="text-center col-xs-2">Tipo Solicitud</th>
                                <th class="text-center col-xs-2">Solicitante</th>
                                <th class="text-center col-xs-2">Propietario</th>
                                <th class="text-center col-xs-2">Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-center col-xs-2"><%=tipoSolicitud%></td>
                                <td class="text-center col-xs-2">
                                    <%
                                    for(int i=0; i<usuariosHtml.size(); i++){
                                        if(usuariosHtml.get(i).getId() == miSolicitud.getIdSolicitante()){
                                    %>
                                            <%=usuariosHtml.get(i).getNombre()%>
                                    <%
                                        }
                                    }
                                    %>
                                </td>
                                <td class="text-center col-xs-2">
                                    <%
                                    for(int i=0; i<usuariosHtml.size(); i++){
                                        if(usuariosHtml.get(i).getId() == miSolicitud.getIdPropietario()){
                                    %>
                                            <%=usuariosHtml.get(i).getNombre()%>
                                    <%
                                        }
                                    }
                                    %>
                                </td>
                                <td class="text-center col-xs-2"><%=estadoSolicitud%></td>
                            </tr>
                        </tbody>
                        
                        <thead class="bg-secondary text-white">
                            <tr>
                                <th class="text-center col-xs-2" colspan="2">Fecha Creaci&oacute;n</th>
                                <th class="text-center col-xs-2" colspan="2">Fecha Modificaci&oacute;n</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-center col-xs-2" colspan="2"><%=miSolicitud.getFechaCreacion()%></td>
                                <td class="text-center col-xs-2" colspan="2"><%=miSolicitud.getFechaModificacion()%></td>
                            </tr>
                        </tbody>
                        
                        <thead class="bg-secondary text-white">
                            <tr>
                                <th class="text-center col-xs-2" colspan="4">Descripci&oacute;n</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-center col-xs-2" colspan="4"><%=miSolicitud.getDescripcion()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </container>
            
            <!-- AGREGAR MENSAJE -->
            <container>
                <div>
                    <form class="agregar-Mensaje" action="AgregarMensaje" method="post" name="myForm">
                        <div>
                            <label class="col-form-label"><Strong>Nuevo Mensaje</Strong></label>
                            <textarea class="form-control" aria-label="Width textarea" name="mensaje" id="mensaje" placeholder="Escriba su mensaje"></textarea>
                            
                            <input type="hidden" id="idSolicitud" name="idSolicitud" value=<%=miSolicitud.getId()%>>
                            <input type="hidden" id="usuarioComenta" name="usuario" value=<%=us%>>
                        </div>
                        <div>
                            <input type="hidden" id="tipoList" name="tipoList" value=<%=list%>>
                            
                            <button id="btnNuevoMensaje" type="submit" class="btn btn-outline-success">Agregar Mensaje</button>
                            <button id="btnCerrarSolicitud" type="submit" class="btn btn-outline-success">Cerrar Solicitud</button>
                        </div>
                    </form>
                </div>
            </container>
            
            <!-- MENSAJES -->
            <container>
                <div>
                    <% for(int i=0; i<mensajesHtml.size(); i++) { %>
                        <div> 
                            <% for(int j=0; j<usuariosHtml.size(); j++) {
                                    if(mensajesHtml.get(i).getIdUsuarioComenta() == usuariosHtml.get(j).getId()){ %>
                                        <label class="col-form-label"><%=usuariosHtml.get(j).getNombre()%></label>
                                <% }
                            } %>
                            &nbsp; &bull; &nbsp;
                            <label class="col-form-label"><%=mensajesHtml.get(i).getFechaCreacion()%></label>
                        </div>
                    
                        <div>
                            <label class="col-form-label"><%=mensajesHtml.get(i).getDescripcion()%></label>
                        </div>
                    <% } %>
                </div>
            </container>
            
        </section>
       
        <!-- FIN CUERPO DE LA PAGINA-->
        <footer class="footer wf-section" th:fragment="footer">
            <div class="col-12 footer-copyright text-center text-white py-3" id="id-Footer">
                <h3 class="text-white font-weight-bold">admin@cfscr.com</h3>
            </div>
        </footer>
    </body>
</html>
