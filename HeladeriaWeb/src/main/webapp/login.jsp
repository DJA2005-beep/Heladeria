<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <style> body{font-family:Arial;margin:30px;} .card{border:1px solid #ccc;padding:16px;border-radius:8px;max-width:400px;} </style>
</head>
<body>
<div class="card">
  <h1>Login</h1>
  <form action="<%=request.getContextPath()%>/login" method="post">
      <label>Usuario:<br/><input type="text" name="usuario" required/></label><br/><br/>
      <label>Password:<br/><input type="password" name="password" required/></label><br/><br/>
      <button type="submit">Entrar</button>
  </form>
  <p style="color:red;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
</div>
</body>
</html>
