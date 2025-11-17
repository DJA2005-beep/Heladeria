/* Me da pereza poner mi...
 * 
 */
package Producto;

import java.sql.ResultSet;

public class Producto {
    private int codigoProducto;
    private String nombreProducto;
    private double precio;
    private int cantidad;
    private int codigoDeBarras;
    private String descripcion;

    public Producto(int codigoProducto, String nombreProducto, double precio, int cantidad, int codigoDeBarras, String descripcion) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigoDeBarras = codigoDeBarras;
        this.descripcion = descripcion;
    }

    public Producto(int aInt, String string, ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    //Getter y Setter
    public int getCodigoProducto() {return codigoProducto;}

    public void setCodigoProducto(int codigoProducto) {this.codigoProducto = codigoProducto;}

    public String getNombreProducto() {return nombreProducto;}

    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto;}

    public double getPrecio() {return precio;}

    public void setPrecio(double precio) {this.precio = precio;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public int getCodigoDeBarras() {return codigoDeBarras;}

    public void setCodigoDeBarras(int codigoDeBarras) {this.codigoDeBarras = codigoDeBarras;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    //to String
    @Override
    public String toString() {
        return "Producto{" + "codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", precio=" + precio + ", cantidad=" + cantidad + ", codigoDeBarras=" + codigoDeBarras + ", descripcion=" + descripcion + '}';
    }   
}
