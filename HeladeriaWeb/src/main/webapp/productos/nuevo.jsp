<%-- 
    Document   : nuevo
    Created on : 13/11/2025, 10:15:29 p. m.
    Author     : alber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Producto - Sugar</title>
    <link rel="stylesheet" href="../css/estilo.css">
    <style>
        .form-container { max-width: 600px; margin: 40px auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #d84315; }
        input, textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
        textarea { height: 80px; resize: vertical; }
        .btn { padding: 12px 25px; background: #4caf50; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; }
        .btn-cancel { background: #f44336; }
        .btn:hover { opacity: 0.9; }
        .header { text-align: center; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="header">
            <h2>Nuevo Producto</h2>
            <p>Usuario: <%= session.getAttribute("usuario") %></p>
        </div>

        <form method="POST" action="../productos">
            <input type="hidden" name="accion" value="guardar">

            <div class="form-group">
                <label>Código Producto *</label>
                <input type="number" name="codigoProducto" required min="1">
            </div>

            <div class="form-group">
                <label>Nombre Producto *</label>
                <input type="text" name="nombreProducto" required maxlength="100">
            </div>

            <div class="form-group">
                <label>Precio *</label>
                <input type="number" step="0.01" name="precio" required min="0">
            </div>

            <div class="form-group">
                <label>Cantidad en Stock *</label>
                <input type="number" name="cantidad" required min="0">
            </div>

            <div class="form-group">
                <label>Código de Barras</label>
                <input type="number" name="codigoDeBarras">
            </div>

            <div class="form-group">
                <label>Descripción</label>
                <textarea name="descripcion"></textarea>
            </div>

            <div style="text-align: center; margin-top: 20px;">
                <button type="submit" class="btn">Guardar Producto</button>
                <a href="../productos" class="btn btn-cancel">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>
