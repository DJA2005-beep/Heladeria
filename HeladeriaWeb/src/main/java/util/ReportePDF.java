/**
 *
 * @author alber
 */

package util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportePDF {

    public static void generarReporte(String rutaArchivo) {
        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título — iText 7 usa setBold() a través de Text(), no Paragraph
            Paragraph titulo = new Paragraph("Reporte de Productos").setFontSize(20);

            document.add(titulo);

            float[] columnas = {100f, 200f, 100f, 100f};
            Table tabla = new Table(columnas);

            tabla.addCell("Código");
            tabla.addCell("Nombre");
            tabla.addCell("Categoría");
            tabla.addCell("Precio");

            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM producto");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tabla.addCell(rs.getString("codigoProducto"));
                tabla.addCell(rs.getString("nombre"));
                tabla.addCell(rs.getString("categoria"));
                tabla.addCell(rs.getString("precio"));
            }

            document.add(tabla);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

