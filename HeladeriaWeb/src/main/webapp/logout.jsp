<%-- 
    Document   : logout
    Created on : 13/11/2025, 10:11:49 p. m.
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
