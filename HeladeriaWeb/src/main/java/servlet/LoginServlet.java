
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Utilidades.HashUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConexionBD;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        if (usuario == null || usuario.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        String hashed = HashUtil.sha256(password);

        // Check against DB
        String sql = "SELECT usuario FROM usuario WHERE usuario = ? AND password = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, hashed);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HttpSession sess = request.getSession();
                    sess.setAttribute("usuario", usuario);
                    response.sendRedirect(request.getContextPath() + "/productos");
                    return;
                } else {
                    request.setAttribute("error", "Usuario o contraseña incorrectos");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
