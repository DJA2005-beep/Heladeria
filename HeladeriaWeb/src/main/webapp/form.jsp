<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Producto" %>
<%
    Producto producto = (Producto) request.getAttribute("producto");
    boolean edit = producto != null;
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><%= edit ? "Editar" : "Nuevo" %> Producto</title>
    <style> body { font-family: Arial; margin:30px; } .card{border:1px solid #ccc;padding:16px;border-radius:8px;max-width:600px; }</style>
</head>
<body>
<div class="card">
  <h1><%= edit ? "Editar" : "Nuevo" %> Producto</h1>
  <form action="<%=request.getContextPath()%>/productos" method="post">
      <input type="hidden" name="action" value="<%= edit ? "update" : "insert" %>"/>
      <% if (edit) { %>
          <input type="hidden" name="codigoProductohidden" value="<%=producto.getCodigoProducto()%>"/>
      <% } %>
      <label>Código:<br/>
          <input type="text" name="codigoProducto" value="<%= edit ? producto.getCodigoProducto() : "" %>" <%= edit ? "readonly" : "required" %>/>
      </label><br/><br/>
      <label>Nombre:<br/>
          <input type="text" name="nombre" value="<%= edit ? producto.getNombre() : "" %>" required/>
      </label><br/><br/>
      <label>Precio:<br/>
          <input type="number" step="0.01" name="precio" value="<%= edit ? producto.getPrecio() : "0.00" %>" required/>
      </label><br/><br/>
      <label>Categoría:<br/>
          <input type="text" name="categoria" value="<%= edit ? producto.getCategoria() : "" %>"/>
      </label><br/><br/>
      <button type="submit"><%= edit ? "Actualizar" : "Guardar" %></button>
  </form>
  <p><a href="<%=request.getContextPath()%>/productos">Volver</a></p>
</div>
</body>
</html>
