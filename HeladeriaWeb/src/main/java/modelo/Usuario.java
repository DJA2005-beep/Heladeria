/**
 *
 * @author alber
 */
package modelo;

public class Usuario {
    private int id;
    private String usuario;
    private String password;

    public Usuario() {}

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
