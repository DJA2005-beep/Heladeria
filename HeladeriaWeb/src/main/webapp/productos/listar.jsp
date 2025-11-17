<%-- 
    Document   : listar
    Created on : 13/11/2025, 10:14:53 p. m.
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario - Sugar</title>
    <style>
        body { font-family: Arial; background: #f5f5f5; margin: 0; padding: 20px; }
        .header { background: #ff6f00; color: white; padding: 15px; text-align: center; border-radius: 8px; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #ffcc80; font-weight: bold; }
        .btn { padding: 8px 12px; background: #4caf50; color: white; text-decoration: none; border-radius: 4px; margin: 0 5px; }
        .btn-edit { background: #2196f3; }
        .btn-delete { background: #f44336; }
        .btn:hover { opacity: 0.8; }
        .logout { text-align: right; margin: 10px 0; }
    </style>
</head>
<body>
    <div class="header">
        <h2>Inventario de Productos</h2>
        <p>Usuario: <%= session.getAttribute("usuario") %></p>
    </div>
    
    <div class="logout">
        <a href="../logout" class="btn">Cerrar Sesión</a>
    </div>
    
    <a href="nuevo.jsp" class="btn">+ Nuevo Producto</a>
    
    <table>
        <tr>
            <th>ID</th><th>Nombre</th><th>Precio</th><th>Stock</th><th>Cód. Barras</th><th>Descripción</th><th>Acciones</th>
        </tr>
        <% 
            List<Producto> productos = (List<Producto>) request.getAttribute("productos");
            if (productos != null) {
                for (Producto p : productos) {
        %>
        <tr>
            <td><%= p.getCodigoProducto() %></td>
            <td><%= p.getNombreProducto() %></td>
            <td>$<%= p.getPrecio() %></td>
            <td><%= p.getCantidad() %></td>
            <td><%= p.getCodigoDeBarras() %></td>
            <td><%= p.getDescripcion() %></td>
            <td>
                <a href="editar.jsp?id=<%= p.getCodigoProducto() %>" class="btn btn-edit">Editar</a>
                <a href="eliminar?id=<%= p.getCodigoProducto() %>" class="btn btn-delete" onclick="return confirm('¿Eliminar?')">Eliminar</a>
            </td>
        </tr>
        <% 
                }
            }
        %>
    </table>
    
    <div style="text-align: center; margin-top: 20px;">
        <a href="../reporte/pdf" class="btn btn-pdf">Generar Reporte PDF</a>
    </div>
</body>
</html>
