<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Producto" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Heladería - Productos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .card { border:1px solid #ccc; padding:16px; border-radius:8px; max-width:900px; }
        table { width:100%; border-collapse: collapse; }
        th, td { border:1px solid #ddd; padding:8px; text-align:left; }
        th { background:#f4f4f4; }
        .actions a { margin-right:8px; }
    </style>
</head>
<body>
<div class="card">
  <h1>Productos</h1>
  <p><a href="<%=request.getContextPath()%>/productos?action=new">Nuevo producto</a> | <a href="<%=request.getContextPath()%>/reporte">Generar PDF</a> | <a href="<%=request.getContextPath()%>/login">Login</a></p>

  <table>
      <tr>
          <th>Código</th><th>Nombre</th><th>Precio</th><th>Categoría</th><th>Acciones</th>
      </tr>
      <%
          List<Producto> lista = (List<Producto>) request.getAttribute("productos");
          if (lista != null) {
              for (Producto p : lista) {
      %>
      <tr>
          <td><%=p.getCodigoProducto()%></td>
          <td><%=p.getNombre()%></td>
          <td><%=String.format("%.2f", p.getPrecio())%></td>
          <td><%=p.getCategoria()%></td>
          <td class="actions">
              <a href="<%=request.getContextPath()%>/productos?action=edit&codigoProducto=<%=p.getCodigoProducto()%>">Editar</a>
              <a href="<%=request.getContextPath()%>/productos?action=delete&codigoProducto=<%=p.getCodigoProducto()%>" onclick="return confirm('Eliminar?')">Eliminar</a>
          </td>
      </tr>
      <%
              }
          }
      %>
  </table>
</div>
</body>
</html>
