package User;

public class UsuarioDoctor {
    private String nombre;
    private String contraseña;
    private String ID;
    private String codigoSeguridad;

    // Constructor
    public UsuarioDoctor(String nombre, String contraseña, String ID, String codigoSeguridad) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.ID = ID;
        this.codigoSeguridad = codigoSeguridad;
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    // Verificar si la identificación del médico es correcta
    public boolean verificarUsuario(String ID, String codigoSeguridad) {
        return this.ID.equals(ID) && this.codigoSeguridad.equals(codigoSeguridad);
    }
}
