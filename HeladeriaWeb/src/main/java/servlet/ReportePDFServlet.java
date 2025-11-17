
package servlet;

import util.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ReportePDFServlet", urlPatterns = {"/reporte"})
public class ReportePDFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        try (Connection conn = ConexionBD.getConexion()) {
            String sql = "SELECT codigoProducto, nombre, precio, categoria FROM producto";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                PdfWriter writer = new PdfWriter(response.getOutputStream());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                document.add(new Paragraph("Listado de productos"));

                Table table = new Table(new float[]{2,4,2,3});
                table.addHeaderCell("Código");
                table.addHeaderCell("Nombre");
                table.addHeaderCell("Precio");
                table.addHeaderCell("Categoría");

                while (rs.next()) {
                    table.addCell(rs.getString("codigoProducto"));
                    table.addCell(rs.getString("nombre"));
                    table.addCell(String.valueOf(rs.getDouble("precio")));
                    table.addCell(rs.getString("categoria"));
                }

                document.add(table);
                document.close();
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
