package ws;

/**
 *
 * @author EVELYN
 */
public class Usuario {
    private String cedula; 
    private String telefono;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String contrasenia;
    private AES_ENCRYPTION encryption; 

    public AES_ENCRYPTION getEncryption() {
        return encryption;
    }

    public void setEncryption(AES_ENCRYPTION encryption) {
        this.encryption = encryption;
    }
    
    public Usuario() {
    }

    public Usuario(String cedula, String telefono, String nombre, String apellido, String email, String direccion, String contrasenia) {
        this.cedula = cedula;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.contrasenia = contrasenia;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    
}
