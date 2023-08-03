<%-- 
    Document   : VerSolicitudes
    Created on : 18 may. 2023, 09:59:02
    Author     : pablo.elizondo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0m maxium-scale=1.0, minium-scale=1.0">
        
        <%@include file = "Templates/Bootstrap.jsp"%>
        
        <link rel="icon" href="Sources/Images/logo_cfs-color.png">
        <link rel="stylesheet" type="text/css" href="Styles/cssVerSolicitudes.css">
        
        <title>CFS Solicitudes</title>
    </head>
    
    <body class="body-Menu">
        <%@include file="Variables/VarVerSolicitudes.jsp"%>
        
        <%@include file="Templates/Header.jsp"%>
        
        <!-- INICIO CUERPO DE LA PAGINA-->
        <section class="section-VerSolicitudes">
            
            <%@include file="Templates/OffCanvas.jsp"%>
            
            <container class="containers">
                
                <%if((list >= 1) && (list <= 3)) {%>
                    <label class="font-weight-bold" id="labelTitulo">Solicitudes que me han realizado</label>
                <%}%>
                <%if((list >= 4) && (list <= 6)) {%>
                    <label class="font-weight-bold" id="labelTitulo">Solicitudes que he realizado</label>
                <%}%>
            
            </container>
                
            <container class="containers" id="container2">
                <!-- Lista desplegable-->
                <div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <label class="labelDropdown font-weight-bold">Seleccionar solicitudes</label>
                    </button>
                    
                    <ul class="dropdown-menu">
                        <li>
                            <form class="ver-Solicitudes" action="SolicitudesUsuario" method="post" name="myForm">
                                <input type="hidden" id="idUs" name="userid" value=<%=us%>>
                                
                                <%if((list >= 1) && (list <= 3)) {%>
                                    <input type="hidden" id="listarTodo" name="listar" value="1">
                                <%}%>
                                <%if((list >= 4) && (list <= 6)) {%>
                                    <input type="hidden" id="listarTodo" name="listar" value="4">
                                <%}%>
                                
                                <input type="submit" name="TodasSolicitudes" value="Todas las Solicitudes" class="btn btn-link">
                            </form>
                        </li>
                        <li>
                            <form class="ver-SolicitudesAbiertas" action="SolicitudesUsuario" method="post" name="myForm">
                                <input type="hidden" id="idUs" name="userid" value=<%=us%>>
                                
                                <%if((list >= 1) && (list <= 3)) {%>
                                    <input type="hidden" id="listarAbiertas" name="listar" value="2">
                                <%}%>
                                <%if((list >= 4) && (list <= 6)) {%>
                                    <input type="hidden" id="listarAbiertas" name="listar" value="5">
                                <%}%>
                                
                                <input type="submit" name="SolicitudesAbiertas" value="Solicitudes Abiertas" class="btn btn-link">
                            </form>
                        </li>
                        <li>
                            <form class="ver-SolicitudesCerradas" actioon="SolicitudesUsuario" method="post" name="myForm">
                                <input type="hidden" id="idUs" name="userid" value=<%=us%>>
                                
                                <%if((list >= 1) && (list <= 3)) {%>
                                    <input type="hidden" id="listarCerrados" name="listar" value="3">
                                <%}%>
                                <%if((list >= 4) && (list <= 6)) {%>
                                    <input type="hidden" id="listarCerrados" name="listar" value="6">
                                <%}%>
                                
                                <input type="submit" name="SolicitudesCerradas" value="Solicitudes Cerradas" class="btn btn-link">
                            </form>
                        </li>
                    </ul>
                </div>
                
                <!-- MODAL AGREGAR SOLICITUD -->
                <%@include file = "Modals/NuevaSolicitud.jsp"%>
                <div class="divModal">
                    <button type="button" class="btn btn-outline-info boton-modal" name="nuevaSolicitud" data-bs-toggle="modal"  data-bs-target="#modalNuevaSolicitud">
                        Nueva Solicitud
                    </button>
                </div>
            </container>
                                
            <!-- TABLA PARA LISTAR LAS SOLICITUDES-->
            <container class="containers">
                <div class="divTable">
                    <table class="table table-fixed table-responsive-lg table-hover" id="tableSolicitudes">
                        
                        <thead class="bg-secondary text-white">
                            <tr class="tr-head-descripcion">
                                <th class="text-center col-xs-2" colspan="8">
                                    
                                    <%if((list == 1) || (list == 4)){ %> Todas las Solicitudes
                                    <%} else if ((list == 2) || (list == 5)) {%> Solicitudes Abiertas
                                    <%} else if((list == 3) || (list == 6)) {%> Solicitudes Cerradas
                                    <%}%>&nbsp;
                                    
                                    <%for(int i=0; i<usuarioHtml.size(); i++) { %>
                                        <% if(usuarioHtml.get(i).getId() == us) {%>
                                            <%=usuarioHtml.get(i).getNombre()%>
                                        <%}%>
                                    <%}%>
                                    
                                </th>
                            </tr>
                            
                            <tr class="tr-head">
                                <th class="text-center col-xs-2">#</th>
                                
                                <th class="text-center col-xs-2">
                                    <%if((list >= 1) && (list <= 3)) {%> Solicitante <%}%>
                                    <%if((list >= 4) && (list <= 6)) {%> Asignado A <%}%>
                                </th>
                                
                                <th class="text-center col-xs-2">ID</th>
                                <th class="text-center col-xs-2">T&iacute;tulo</th>
                                <th class="text-center col-xs-2">Tipo</th>
                                <th class="text-center col-xs-2">Estado</th>
                                <th class="text-center col-xs-2">Fecha Modificaci&oacute;n</th>
                                <th class="text-center col-xs-2">Fecha Creaci&oacute;n</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        <% for(int i=0; i<solicitudHtml.size(); i++) { %>
                            
                            <tr class="tr-tbody">
                                <td class="text-center col-xs-2"><%=i+1%></td>
                                
                                
                                <td class="text-center col-xs-2">
                                
                                    <% for(int j=0; j<usuarioHtml.size(); j++){ %>
                                        
                                        <% if( (list >= 1) && (list <= 3) && (solicitudHtml.get(i).getIdSolicitante() == usuarioHtml.get(j).getId()) ){ %>
                                            <%=usuarioHtml.get(j).getNombre()%>
                                        <%}%>
                                        
                                        <% if( (list >= 4) && (list <= 6) && (solicitudHtml).get(i).getIdPropietario() == usuarioHtml.get(j).getId() ) {%>
                                            <%=usuarioHtml.get(j).getNombre()%>
                                        <%}%>
                                    <%}%>
                                    
                                </td>
                                
                                
                                <td class="text-center col-xs-2"><%=solicitudHtml.get(i).getId()%></td>
                                <td class="text-center col-xs-2">
                                    <!-- Formulario ver detalles de solicitud -->
                                    <form action="VerSolicitud" method="post" name="myForm">
                                        <input type="hidden" name="idSolicitud" value=<%=solicitudHtml.get(i).getId()%>>
                                        <input type="hidden" name="listar" value=<%=list%>>
                                        <input type="hidden" name="userid" value=<%=us%>>
                                        
                                        <button id="botonConsultaSolicitud" type="submit" style="none">
                                            <%=solicitudHtml.get(i).getTitulo()%>
                                        </button>
                                    </form>
                                </td>
                                <td class="text-center col-xs-2">
                                    <% for(int j=0; j<tipoSolicitudHtml.size(); j++){
                                        if(solicitudHtml.get(i).getIdTipo() == tipoSolicitudHtml.get(j).getId()){ %>
                                            <%=tipoSolicitudHtml.get(j).getNombre()%>
                                        <%}%> 
                                    <%}%>
                                </td>
                                <td class="text-center col-xs-2">
                                    <% for(int j=0; j<estSolicitudHtml.size(); j++) {
                                        if(solicitudHtml.get(i).getEstado() == estSolicitudHtml.get(j).getId()) { %>
                                            <%=estSolicitudHtml.get(j).getNombre()%>
                                        <%}%>
                                    <%}%>
                                </td>
                                <td class="text-center col-xs-2"><%=solicitudHtml.get(i).getFechaCreacion()%></td>
                                <td class="text-center col-xs-2"><%=solicitudHtml.get(i).getFechaModificacion()%></td>
                            </tr>
                                
                        <%}%>
                        </tbody>
                        
                    </table>
                </div>
            </container>
        </section>

        <!-- FIN CUEPPO DE LA PAGINA-->
        <footer class="footer wf-section" th:fragment="footer">
            <div class="col-12 footer-copyright text-center text-white py-3" id="id-Footer">
                <h3 class="text-white font-weight-bold">admin@cfscr.com</h3>
            </div>
        </footer>
    </body>
</html>
