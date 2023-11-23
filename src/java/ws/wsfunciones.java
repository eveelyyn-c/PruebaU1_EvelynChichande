
package ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author EVELYN
 */
@WebService(serviceName = "Wsfunciones")
public class wsfunciones {

    private final List<Usuario> usuarios = new ArrayList<>();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation() {
        return null;
    }

    @WebMethod(operationName = "RegistrarUsuarios")
    public boolean registerUser(@WebParam(name = "cedula") String cedula,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "email") String email,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "contrasenia") String contrasenia) {
        try {
            //verifico si el usuario se encuentra registrado
            for (Usuario existingUser : usuarios) {
                if (existingUser.getCedula().equals(cedula)) {
                    return false;
                }
            }

            AES_ENCRYPTION encryption = new AES_ENCRYPTION();
            encryption.init();
            String encryptedPassword = encryption.encrypt(contrasenia);
            Usuario newusuario = new Usuario(cedula, nombre, apellido, email, direccion, contrasenia, encryptedPassword);
            newusuario.setEncryption(encryption);
            //usuarios.add(new Usuario("0803709997", "Evelyn", "Chichande" , "Alejo@gmail.com", "Cuenca", "12345"));
            usuarios.add(newusuario);
            System.out.println("Contraseña encriptada: " + encryptedPassword);
            System.out.println("Registro exitoso");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @WebMethod(operationName = "login")
    public Usuario login(@WebParam(name = "cedula") String cedula, @WebParam(name = "contrasenia") String contrasenia) {
        try {
            AES_ENCRYPTION encryption = new AES_ENCRYPTION();
            encryption.init();

            for (Usuario user : usuarios) {
                AES_ENCRYPTION userEncryption = user.getEncryption();
                if (user.getCedula().equals(cedula) && decryptPassword(user.getContrasenia(), userEncryption).equals(contrasenia)) {
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private String decryptPassword(String encryptedPassword, AES_ENCRYPTION encryption) {
        try {
            if (encryption != null) {
                return encryption.decrypt(encryptedPassword);
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
 
    @WebMethod(operationName = "subirProducto")
    public String subirProducto(Producto producto) {
        return "";
    }

    @WebMethod(operationName = "actualizarIinventario")
    public String actualizarInventario(int productoId, int cantidad) {
        return "";
    }

    @WebMethod
    public String asignarProveedor(int productoId, int proveedorId) {
        return "Proveedor asignado exitosamente.";
    }

    @WebMethod
    public String actualizarCompra(int compraId, Compra compra) {
        return "Compra actualizada exitosamente.";
    }

    @WebMethod
    public String registrarVenta(Venta venta) {
        return "Venta registrada exitosamente.";
    }

    @WebMethod
    public String obtenerVentas() {
        return "Ventas obtenidas exitosamente.";
    }

}
