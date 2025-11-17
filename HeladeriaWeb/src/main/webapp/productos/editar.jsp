<%-- 
    Document   : editar
    Created on : 13/11/2025, 11:02:11 p. m.
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Producto"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Producto - Sugar</title>
    <link rel="stylesheet" href="../css/estilo.css">
    <style>
        .form-container { max-width: 600px; margin: 40px auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #d84315; }
        input, textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
        textarea { height: 80px; resize: vertical; }
        .btn { padding: 12px 25px; background: #2196f3; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }
        .btn-cancel { background: #f44336; }
        .btn:hover { opacity: 0.9; }
        .header { text-align: center; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="header">
            <h2>Editar Producto</h2>
            <p>Usuario: <%= session.getAttribute("usuario") %></p>
        </div>

        <%
            Producto p = (Producto) request.getAttribute("producto");
            if (p == null) {
                out.println("<p style='color:red; text-align:center;'>Producto no encontrado.</p>");
                return;
            }
        %>

        <form method="POST" action="../productos">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="codigoProducto" value="<%= p.getCodigoProducto() %>">

            <div class="form-group">
                <label>Código Producto</label>
                <input type="number" value="<%= p.getCodigoProducto() %>" disabled>
                <small style="color:#666;">(No editable)</small>
            </div>

            <div class="form-group">
                <label>Nombre Producto *</label>
                <input type="text" name="nombreProducto" value="<%= p.getNombreProducto() %>" required maxlength="100">
            </div>

            <div class="form-group">
                <label>Precio *</label>
                <input type="number" step="0.01" name="precio" value="<%= p.getPrecio() %>" required min="0">
            </div>

            <div class="form-group">
                <label>Cantidad en Stock *</label>
                <input type="number" name="cantidad" value="<%= p.getCantidad() %>" required min="0">
            </div>

            <div class="form-group">
                <label>Código de Barras</label>
                <input type="number" name="codigoDeBarras" value="<%= p.getCodigoDeBarras() %>">
            </div>

            <div class="form-group">
                <label>Descripción</label>
                <textarea name="descripcion"><%= p.getDescripcion() != null ? p.getDescripcion() : "" %></textarea>
            </div>

            <div style="text-align: center; margin-top: 20px;">
                <button type="submit" class="btn">Actualizar Producto</button>
                <a href="../productos" class="btn btn-cancel">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>
