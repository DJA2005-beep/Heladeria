package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static Connection conexion = null;

    private static final String URL = "jdbc:mariadb://localhost:3306/heladeria";
    private static final String USER = "root";   // Laragon
    private static final String PASSWORD = "3nd0rs1";

    private ConexionBD() {}

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}


