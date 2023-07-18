<%@page import="java.util.ArrayList"%>

<%@page import="com.cfscr.solicitudes.entities.Usuario"%>
<%@page import="com.cfscr.solicitudes.entities.Solicitud"%>
<%@page import="com.cfscr.solicitudes.entities.TipoSolicitud" %>
<%@page import="com.cfscr.solicitudes.entities.EstadoSolicitud"%>

<jsp:useBean id="listUsuario" scope="request" type="java.util.ArrayList" />
<jsp:useBean id="listSolicitud" scope="request" type="java.util.ArrayList" />
<jsp:useBean id="listEstSolicitud" scope="request" type="java.util.ArrayList" />
<jsp:useBean id="listTipoSolicitud" scope="request" type="java.util.ArrayList" />      

<%ArrayList<Usuario> usuarioHtml = listUsuario;%>
<%ArrayList<Solicitud> solicitudHtml = listSolicitud;%>
<%ArrayList<EstadoSolicitud> estSolicitudHtml = listEstSolicitud;%>
<%ArrayList<TipoSolicitud> tipoSolicitudHtml = listTipoSolicitud;%>

<%int us = (Integer) session.getAttribute("userid");%>
<%int list = (Integer) session.getAttribute("listar");%>

<!-- 
                     PRINCIPAL | OF CANVAS | MODAL
usuarioHtml       ->    x                       x       listUsuario
solicitudHtml     ->    x                               listSolicitud
estSolicitudHtml  ->    x                               listEstSolicitud
tipoSolicitudHtml ->    x                       x       listTipoSolicitud
us                ->    x           x           x       userid
list              ->    x                       x       listar
-->