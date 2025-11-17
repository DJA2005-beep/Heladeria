
package servlet;

import modelo.Producto;
import util.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/form.jsp").forward(request, response);
                    break;
                case "edit":
                    mostrarEditar(request, response);
                    break;
                case "delete":
                    eliminar(request, response);
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "insert";

        try {
            switch (action) {
                case "insert":
                    insertar(request, response);
                    break;
                case "update":
                    actualizar(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/productos");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT codigoProducto, nombre, precio, categoria FROM producto";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigoProducto(rs.getString("codigoProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }
        }
        request.setAttribute("productos", lista);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigo = request.getParameter("codigoProducto");
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String categoria = request.getParameter("categoria");

        String sql = "INSERT INTO producto (codigoProducto, nombre, precio, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setDouble(3, precio);
            ps.setString(4, categoria);
            ps.executeUpdate();
        }
        response.sendRedirect(request.getContextPath() + "/productos");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigo = request.getParameter("codigoProducto");
        String sql = "SELECT codigoProducto, nombre, precio, categoria FROM producto WHERE codigoProducto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto(
                        rs.getString("codigoProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("categoria")
                    );
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("/form.jsp").forward(request, response);
                    return;
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/productos");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigo = request.getParameter("codigoProductohidden");
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String categoria = request.getParameter("categoria");

        String sql = "UPDATE producto SET nombre = ?, precio = ?, categoria = ? WHERE codigoProducto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setString(3, categoria);
            ps.setString(4, codigo);
            ps.executeUpdate();
        }
        response.sendRedirect(request.getContextPath() + "/productos");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String codigo = request.getParameter("codigoProducto");
        String sql = "DELETE FROM producto WHERE codigoProducto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        }
        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
