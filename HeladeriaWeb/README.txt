HeladeriaWeb - Updated version (integrated HashUtil, ReportePDFServlet, JSPs styled like PrimeraAplicacion)

What I changed:
- Integrated Utilidades/HashUtil.java (SHA-256 hashing) and adjusted LoginServlet to validate hashed passwords.
- Added ReportePDFServlet that exports products to PDF using iText 7.
- Updated JSPs (index, form, login) to a card layout resembling PrimeraAplicacion's UI.
- Kept codigoProducto as primary key in producto table.

DB Setup (MariaDB):
CREATE DATABASE IF NOT EXISTS heladeria;
USE heladeria;

CREATE TABLE producto (
    codigoProducto VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    categoria VARCHAR(100)
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    usuario VARCHAR(50) UNIQUE,
    password VARCHAR(200) -- store SHA-256 hashed passwords
);

To create a user (example password 'admin'):
INSERT INTO usuario (nombre, usuario, password) VALUES ('Admin','admin', '<SHA256 of admin>');

How to build & run:
- Import into NetBeans or build with Maven: mvn clean package
- Deploy the generated WAR to Tomcat 10 (Jakarta 9)
- Ensure MariaDB is running and credentials in util/ConexionBD.java match your setup
- Access: http://localhost:8080/HeladeriaWeb/productos

Notes:
- HashUtil.java is in package Utilidades to match your PrimeraAplicacion structure.
- Login is now validated against the hashed password stored in DB.
- ReportePDFServlet produces a simple table PDF; you can modify its style further.
