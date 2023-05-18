<%-- 
    Document   : NuevaSolicitud
    Created on : 18 may. 2023, 09:38:24
    Author     : pablo.elizondo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.cfscr.solicitudes.entities.TipoSolicitud" %>
<%@page import="com.cfscr.solicitudes.entities.Usuario"%>

<jsp:useBean id="listTipoSolicitud" scope="request" type="java.util.ArrayList" />
<jsp:useBean id="listUsuario" scope="request" type="java.util.ArrayList" />

<%ArrayList<TipoSolicitud> tipoSolicitudHtml = listTipoSolicitud;%>
<%ArrayList<Usuario> usuarioHtml = listUsuario;%>

<div class="modal fade" id="modalNuevaSolicitud" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            
            <!-- MODAL HEADER-->
            <div class="modal-header">
                <h5 class="modal-title fs-5" id="staticBackdropLabel">Nueva Solicitud</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            <!-- FORM -->
            <form class="form-NuevaSolicitud" action="AgregarSolicitud" method="post" name="myForm">
                <div class="modal-body">
                    <label class="col-form-label"><Strong>Solicitud</strong></label>
                    <input type="text" class="form-control" pattern="^[A-Za-záéíóúÁÉÍÓÚñÑ ,/\-()]+$" title="Solo digitar letras" maxlength="50" required="true" placeholder="Título" id="tituloSolicitud" name="tituloSolicitud"/>

                    <label class="col-form-label"><Strong>Tipo</Strong></label>
                    <select class="form-select" required="true" name="TipoSolicitud" id="TipoSolicitud">
                        
                        <% for(int i=0; i<tipoSolicitudHtml.size(); i++){ %>
                                <option value=<%=tipoSolicitudHtml.get(i).getId()%>><%=tipoSolicitudHtml.get(i).getNombre()%></option>
                        <% } %>
                        
                    </select>
                    
                    <%int user = (Integer) session.getAttribute("us");%>
                    <input type="hidden" id="idUs" name="idUs" value=<%=user%>>
                    
                    <label class="col-form-label"><Strong>Descripci&oacute;n</Strong></label>
                    <textarea class="form-control" aria-label="With textarea" required="true" name="Descripcion" id="Descripcion"></textarea>
                    
                    <label class="col-form-control"><Strong>Asignar A:</Strong></label>
                    <select class="form-select" required="true" name="AsignarA" id="AsignarA">
                       
                        <% for(int i=0; i<usuarioHtml.size(); i++){ %>
                            <option value=<%=usuarioHtml.get(i).getId()%>><%=usuarioHtml.get(i).getNombre()%></option>
                        <% } %>
                        
                    </select>
                    
                    <%int tipoLista = (Integer) session.getAttribute("list");%>
                    <input type="hidden" id="tipoList" name="tipoList" value=<%=tipoLista%>>
                </div>
                                    
                <div class="modal-footer">
                    <button id="btnNuevaSolicitud" type="submit" class="btn btn-outline-success">Aceptar</button>
                    <button id="btnNoModificarSolicitud" type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </form>
            
        </div>
    </div>
</div>