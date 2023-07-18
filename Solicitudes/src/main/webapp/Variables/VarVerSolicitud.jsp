<%@page import="java.util.ArrayList" %>

<%@page import="com.cfscr.solicitudes.entities.Mensaje" %>
<%@page import="com.cfscr.solicitudes.entities.Usuario" %>
<%@page import="com.cfscr.solicitudes.entities.Solicitud" %>

<jsp:useBean id="mensajes" scope="request" type="java.util.ArrayList"/>
<jsp:useBean id="usuarios" scope="request" type="java.util.ArrayList"/>
        
<%ArrayList<Mensaje> mensajesHtml = mensajes;%>
<%ArrayList<Usuario> usuariosHtml = usuarios;%>
        
<%int us = (Integer) session.getAttribute("userid");%>
<%int list = (Integer) session.getAttribute("listar");%>
<%String tipoSolicitud = (String) session.getAttribute("tipoSolicitud");%>
<%String estadoSolicitud = (String) session.getAttribute("estadoSolicitud");%>
        
<%Solicitud miSolicitud = (Solicitud) session.getAttribute("solicitud");%>