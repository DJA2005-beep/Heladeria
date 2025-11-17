// src/Utilidades/ReportePDF.java
package Utilidades;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import conexionBD.ConexionBD;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportePDF {

    public static void generar(OutputStream out, String rutaLogo) {
        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // === ENCABEZADO ===
            document.add(new Paragraph("HELADERÍA SUGAR")
                .setFontSize(24).setBold().setTextAlignment(TextAlignment.CENTER));

            if (rutaLogo != null && !rutaLogo.isEmpty()) {
                try {
                    // USAR getResource() PARA CLASSPATH
                    java.net.URL imgUrl = ReportePDF.class.getResource(rutaLogo);
                    if (imgUrl != null) {
                        Image img = new Image(ImageDataFactory.create(imgUrl));
                        img.setWidth(UnitValue.createPointValue(120));
                        img.setHorizontalAlignment(HorizontalAlignment.CENTER);
                        document.add(img);
                    } else {
                        document.add(new Paragraph("[Logo no encontrado en classpath]").setFontSize(10));
                    }
                } catch (Exception e) {
                    document.add(new Paragraph("[Error al cargar logo]").setFontSize(10));
                    e.printStackTrace();
                }
            }

            document.add(new Paragraph("Av. Dulce 123, Col. Sabores")
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Tel: (222) 1234-5678")
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("contacto@sugarhelados.com")
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()))
                .setFontSize(12).setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // === TÍTULO ===
            document.add(new Paragraph("REPORTE DE INVENTARIO")
                .setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));

            // === TABLA ===
            float[] widths = {40, 150, 70, 70, 90, 160};
            Table table = new Table(UnitValue.createPercentArray(widths));
            table.setWidth(UnitValue.createPercentValue(100));

            String[] headers = {"ID", "Nombre", "Precio", "Stock", "Cód. Barras", "Descripción"};
            for (String h : headers) {
                table.addHeaderCell(new Cell()
                    .add(new Paragraph(h).setBold())
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY));
            }

            double valorTotal = 0;
            int totalStock = 0;
            int filas = 0;

            try (Connection c = ConexionBD.conectar();
                 Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM productos ORDER BY codigoProducto")) {

                while (rs.next()) {
                    int id = rs.getInt("codigoProducto");
                    String nombre = rs.getString("nombreProducto");
                    double precio = rs.getDouble("precio");
                    int stock = rs.getInt("cantidad");
                    int barras = rs.getInt("codigoDeBarras");
                    String desc = rs.getString("descripcion");

                    table.addCell(new Paragraph(String.valueOf(id)));
                    table.addCell(new Paragraph(nombre != null ? nombre : "N/A"));
                    table.addCell(new Paragraph(String.format("%.2f", precio)));
                    table.addCell(new Paragraph(String.valueOf(stock)));
                    table.addCell(new Paragraph(String.valueOf(barras)));
                    table.addCell(new Paragraph(desc != null ? desc : "Sin descripción"));

                    valorTotal += precio * stock;
                    totalStock += stock;
                    filas++;
                }
            } catch (SQLException e) {
                System.err.println("ERROR BD: " + e.getMessage());
                document.add(new Paragraph("ERROR: No se pudo cargar datos.")
                    .setFontColor(ColorConstants.RED));
            }

            document.add(table);

            // === RESUMEN ===
            document.add(new Paragraph("\nRESUMEN").setFontSize(14).setBold());
            document.add(new Paragraph("• Productos: " + filas));
            document.add(new Paragraph("• Total stock: " + totalStock));
            document.add(new Paragraph("• Valor total: $" + String.format("%.2f", valorTotal)));

            if (filas == 0) {
                document.add(new Paragraph("\nNo hay productos registrados.")
                    .setFontColor(ColorConstants.ORANGE));
            }

            document.close();
            System.out.println("PDF generado. Filas: " + filas);

        } catch (Exception e) {
            System.err.println("ERROR PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}