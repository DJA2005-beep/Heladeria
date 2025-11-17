<%-- 
    Document   : dashboard
    Created on : 13/11/2025, 10:10:28 p. m.
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Sugar</title>
    <style>
        body { font-family: Arial; background: #f5f5f5; margin: 0; padding: 20px; }
        .header { background: #ff6f00; color: white; padding: 20px; text-align: center; border-radius: 10px; margin-bottom: 20px; }
        .menu { display: flex; justify-content: center; gap: 20px; margin: 20px 0; }
        .btn { padding: 15px 30px; background: #d84315; color: white; text-decoration: none; border-radius: 8px; font-weight: bold; }
        .btn:hover { background: #b71c1c; }
        .btn-pdf { background: #1976d2; }
        .btn-logout { background: #f44336; }
        .logout { text-align: center; margin-top: 30px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Bienvenido a Heladería Sugar</h1>
        <p>Gestión de Inventario</p>
    </div>
    
    <div class="menu">
        <a href="productos" class="btn">Inventario</a>
        <a href="reporte/pdf" class="btn btn-pdf">Reporte PDF</a>
        <a href="logout" class="btn btn-logout">Cerrar Sesión</a>
    </div>
    
    <div class="logout">
        <p><small>Usuario: <%= session.getAttribute("usuario") %></small></p>
    </div>
</body>
</html>
