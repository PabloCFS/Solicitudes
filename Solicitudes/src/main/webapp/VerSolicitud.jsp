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
         
        <link rel="icon" href="Sources/Images/logo_cfs-color.png">
        <link rel="stylesheet" type="text/css" href="Styles/cssVerSolicitud.css">
        
        <title>CFS Solicitudes</title>
    </head>
    
    <body class="body-menu">

        <%@include file="Templates/Header.jsp"%>
        
        <%@include file="Variables/VarVerSolicitud.jsp"%>
        
        <!-- INICIO CUERPO DE LA PAGINA -->
        <section class="sectionVerSolicitud">
            
            <container class="containers">
                <label class="font-weight-bold" id="labelTitulo"><%=miSolicitud.getId()%> - <%=miSolicitud.getTitulo()%></label>
            </container>
            
            <container class="containers" id="container2">
                <div class="DivBtn-closeSolicitud">
                    <form class="cerrarSolicitud" action="CerrarSolicitud" method="post" name="myForm">
                        <div>
                            <input type="hidden" id="idSolicitud" name="idSolicitud" value=<%=miSolicitud.getId()%>>
                            <input type="hidden" id="usuarioComenta" name="userid" value=<%=us%>>
                            <input type="hidden" id="tipoList" name="listar" value=<%=list%>>
                            
                            <div class="divCerrarSolicitud">
                                <% if(estadoSolicitud.equals("Asignado")) { %>
                                    <button id="btnCerrarSolicitud" type="submit" class="btn btn-outline-warning">Cerrar Solicitud</button>
                                <% } else if (estadoSolicitud.equals("Cerrado")) {%>
                                    <button id="btnCerrarSolicitud" type="submit" class="btn btn-outline-warning disabled" aria-disable="true" disabled>Cerrar Solicitud</button>
                                <% } %>
                            </div>
                        </div>
                    </form>
                </div>
            </container>
                            
            <!-- DATOS SOLICITUD - TABLA -->
            <container class="containerVerSolicitud">
                <div class="divTableVerSolicitud">
                    <table class="table table-fixed table-responsive-lg table-hover">
                        
                        <thead class="bg-secondary text-white">
                            
                            <tr class="tr-head">
                                <th class="text-center col-xs-2">Tipo Solicitud</th>
                                <th class="text-center col-xs-2">Solicitante</th>
                                <th class="text-center col-xs-2">Propietario</th>
                                <th class="text-center col-xs-2">Fecha Creaci&oacute;n</th>
                                <th class="text-center col-xs-2">Fecha Modificaci&oacute;n</th>
                                <th class="text-center col-xs-2">Estado</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr class="tr-tbody">
                                <td class="text-center col-xs-2"><%=tipoSolicitud%></td>
                                <td class="text-center col-xs-2">
                                    <% for(int i=0; i<usuariosHtml.size(); i++){
                                        if(usuariosHtml.get(i).getId() == miSolicitud.getIdSolicitante()){ %>
                                            <%=usuariosHtml.get(i).getNombre()%>
                                        <%}%>
                                    <%}%>
                                </td>
                                <td class="text-center col-xs-2">
                                    <% for(int i=0; i<usuariosHtml.size(); i++){
                                        if(usuariosHtml.get(i).getId() == miSolicitud.getIdPropietario()){ %>
                                            <%=usuariosHtml.get(i).getNombre()%>
                                        <%}%>
                                    <%}%>
                                </td>
                                <td class="text-center col-xs-2"><%=miSolicitud.getFechaCreacion()%></td>
                                <td class="text-center col-xs-2"><%=miSolicitud.getFechaModificacion()%></td>
                                <td class="text-center col-xs-2"><%=estadoSolicitud%></td>
                            </tr>
                        </tbody>
                        
                        <thead class="bg-secondary text-white">
                            <tr class="tr-head">
                                <th class="text-center col-xs-2" colspan="6">Descripci&oacute;n</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr class="tr-tbody">
                                <td class="text-center col-xs-2" colspan="6"><%=miSolicitud.getDescripcion()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>       
            </container>
            
            <!-- MODAL AGREGAR MENSAJE -->
            <%@include file = "Modals/NuevoMensaje.jsp"%>
            <container class="containerVerSolicitud">
                <div class="divModal">
                    <% if(estadoSolicitud.equals("Asignado")) { %>
                        <button type="button" class="btn btn-outline-light boton-modal" name="nuevoMensaje" data-bs-toggle="modal" data-bs-target="#modalNuevoMensaje">Nuevo Mensaje</button>
                    <%} else if (estadoSolicitud.equals("Cerrado")) {%>
                        <button type="button" class="btn btn-outline-light boton-modal disabled" name="nuevoMensaje" data-bs-toggle="modal" data-bs-target="#modalNuevoMensaje" aria-disable="true" disabled>Nuevo Mensaje</button>
                    <%}%>
                </div>
            </container>
            
            <!-- MENSAJES -->
            <container class="containerVerSolicitud">
                <div class="divMensajes">
                    <% for(int i = mensajesHtml.size()-1; i >= 0; i--) { %>
                        
                        <div class="divsMensajes">
                            <div class="divsMensajes-Encabezado">
                                
                                <% for(int j=0; j<usuariosHtml.size(); j++) {
                                    if(mensajesHtml.get(i).getIdUsuarioComenta() == usuariosHtml.get(j).getId()){ %>
                                        <label class="col-form-label" id="label-Mensajes-Encabezado"><%=usuariosHtml.get(j).getNombre()%></label>
                                    <%}%>
                                <%}%>
                                <label class="col-form-label" id="label-Mensajes-Encabezado">&nbsp;&bull;&nbsp;</label>
                                <label class="col-form-label" id="label-Mensajes-Encabezado"><%=mensajesHtml.get(i).getFechaCreacion()%></label>
                            </div>
                    
                            <div class="divsMensajes-Cuerpo">
                                <label class="col-form-label" id="label-Mensajes-Cuerpo"><%=mensajesHtml.get(i).getDescripcion()%></label>
                            </div>
                        </div>
                            
                    <%}%>
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
