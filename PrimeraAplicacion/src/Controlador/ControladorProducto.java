package controlador;

import conexionBD.ConexionBD;
import Producto.Producto;
import java.sql.*;
import javax.swing.JOptionPane;

public class ControladorProducto {

    public static boolean crear(Producto p) {
        String sql = "INSERT INTO productos (nombreProducto, precio, cantidad, codigoDeBarras, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = ConexionBD.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNombreProducto());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCodigoDeBarras());
            ps.setString(5, p.getDescripcion());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear: " + e.getMessage());
            return false;
        }
    }

    public static boolean actualizar(Producto p) {
        String sql = "UPDATE productos SET nombreProducto=?, precio=?, cantidad=?, codigoDeBarras=?, descripcion=? WHERE codigoProducto=?";
        try (Connection c = ConexionBD.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNombreProducto());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCodigoDeBarras());
            ps.setString(5, p.getDescripcion());
            ps.setInt(6, p.getCodigoProducto());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Producto actualizado.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto con ID: " + p.getCodigoProducto());
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public static boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE codigoProducto = ?";
        try (Connection c = ConexionBD.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto con ID: " + id);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public static Producto buscarPorId(int id) {
        String sql = "SELECT * FROM productos WHERE codigoProducto = ?";
        try (Connection c = ConexionBD.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("codigoProducto"),
                    rs.getString("nombreProducto"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad"),
                    rs.getInt("codigoDeBarras"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + e.getMessage());
        }
        return null;
    }
}
