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
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1 maxium-scale=1.0, minium-scale=1.0">
        
        <%@include file = "Templates/Bootstrap.jsp"%>
        <%@include file = "Modals/NuevaSolicitud.jsp"%>
        
        <link rel="stylesheet" type="text/css" href="Styles/cssVerSolicitudes.css">
        <link rel="icon" href="Sources/Images/logo_cfs-color.png">        
        <title>CFS Solicitudes</title>
    </head>
    
    <body class="body-Menu">
        <%@include file="Templates/Header.jsp"%>
        
        <!-- INICIO CUERPO DE LA PAGINA-->
        <section class="section-VerSolicitudes">
            <container class="containers">
                <label class="font-weight-bold" id="labelTitulo">Gesti&oacute;n Solicitudes</label>
            </container>
                
            <%int us = (Integer) session.getAttribute("us");%>
            <container class="containers" id="container2">
                <!-- Lista desplegable-->
                <div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <label class="labelDropdown font-weight-bold">Selecionar solicitudes</label>
                    </button>
                    <ul class="dropdown-menu">
                        <li>
                            <form class="ver-Solicitudes" action="SolicitudesUsuario" method="post" name="myForm">
                                <input type="hidden" id="idUs" name="idUs" value=<%=us%>>
                                <input type="hidden" id="listarTodo" name="listar" value="1">
                                <input type="submit" name="TodasSolicitudes" value="Todas las Solicitudes" class="btn btn-link">
                            </form>
                        </li>
                        <li>
                            <form class="ver-SolicitudesAbiertas" action="SolicitudesUsuario" method="post" name="myForm">
                                <input type="hidden" id="idUs" name="idUs" value=<%=us%>>
                                <input type="hidden" id="listarAbiertas" name="listar" value="2">
                                <input type="submit" name="SolicitudesAbiertas" value="Solicitudes Abiertas" class="btn btn-link">
                            </form>
                        </li>
                        <li>
                            <form class="ver-SolicitudesCerradas" actioon="SolicitudesUsuario" method="post" name="myForm">
                                <input type="hidden" id="idUs" name="idUs" value=<%=us%>>
                                <input type="hidden" id="listarCerrados" name="listar" value="3">
                                <input type="submit" name="SolicitudesCerradas" value="Solicitudes Cerradas" class="btn btn-link">
                            </form>
                        </li>
                    </ul>
                </div>
                
                <!-- Boton modal para registro de caso-->
                <div class="divModal">
                    <!-- MODAL-->
                    <button type="button" class="btn btn-outline-info boton-modal" name="nuevaSolicitud" data-bs-toggle="modal"  data-bs-target="#modalNuevaSolicitud">
                        Nueva Solicitud
                    </button>
                    <!-- MODAL-->
                </div>
            </container>
                
            <%@page import="com.cfscr.solicitudes.entities.Solicitud"%>
            <%@page import="com.cfscr.solicitudes.entities.EstadoSolicitud"%>
            
            <jsp:useBean id="listSolicitud" scope="request" type="java.util.ArrayList" />
            <jsp:useBean id="listEstSolicitud" scope="request" type="java.util.ArrayList" />
            
            <%ArrayList<Solicitud> solicitudHtml = listSolicitud;%>
            <%ArrayList<EstadoSolicitud> estSolicitudHtml = listEstSolicitud;%>
            <%int list = (Integer) session.getAttribute("list");%>
            <!-- TABLA PARA LISTAR LAS SOLICITUDES-->
            <container class="containers">
                <div class="divTable">
                        
                    <table class="table table-fixed table-responsive-lg table-hover" id="tableSolicitudes">
                        <thead class="bg-secondary text-white">
                            <tr class="tr-head-descipcion">
                                <th class="text-center col-xs-2" colspan="8">
                                    
                                    <%if(list == 1){%>
                                        Todas las Solicitudes
                                        
                                    <%} else if (list == 2) {%>
                                        Solicitudes Abiertas
                                        
                                    <%} else if(list == 3) {%>
                                        Solicitudes Cerradas
                                    <%}%>
                                </th>
                            </tr>
                            
                            <tr class="tr-head">
                                <th class="text-center col-xs-2">#</th>
                                <th class="text-center col-xs-2">Solicitante</th>
                                <th class="text-center col-xs-2">ID</th>
                                <th class="text-center col-xs-2">T&iacute;tulo</th>
                                <th class="text-center col-xs-2">Tipo</th>
                                <th class="text-center col-xs-2">Estado</th>
                                <th class="text-center col-xs-2">Fecha Modificaci&oacute;n</th>
                                <th class="text-center col-xs-2">Fecha Creaci&oacute;n</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <%
                                for(int i=0; i<solicitudHtml.size(); i++) {
                            %>
                            <tr class="tr-tbody">
                                <td class="text-center col-xs-2"><%=i+1%></td>
                                <td class="text-center col-xs-2">
                                    <%
                                        for(int j=0; j<usuarioHtml.size(); j++){
                                            if(solicitudHtml.get(i).getIdSolicitante() == usuarioHtml.get(j).getId()){
                                    %>
                                        <%=usuarioHtml.get(j).getNombre()%>
                                    <%      }
                                        } 
                                    %>
                                </td>
                                <td class="text-center col-xs-2"><%=solicitudHtml.get(i).getId()%></td>
                                <td class="text-center col-xs-2"><%=solicitudHtml.get(i).getTitulo()%></td>
                                <td class="text-center col-xs-2">
                                    <%
                                        for(int j=0; j<tipoSolicitudHtml.size(); j++){
                                            if(solicitudHtml.get(i).getIdTipo() == tipoSolicitudHtml.get(j).getId()){
                                    %>
                                        <%=tipoSolicitudHtml.get(j).getNombre()%>
                                    <%      }
                                        }
                                    %>
                                </td>
                                <td class="text-center col-xs-2">
                                    <%
                                        for(int j=0; j<estSolicitudHtml.size(); j++) {
                                            if(solicitudHtml.get(i).getEstado() == estSolicitudHtml.get(j).getId()) {
                                    %>
                                        <%=estSolicitudHtml.get(j).getNombre()%>
                                    <%      }
                                        }
                                    %>
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
