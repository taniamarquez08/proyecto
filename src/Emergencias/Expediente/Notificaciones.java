package Expediente;
import User.*;

public class Notificaciones {
    private String mensaje;
    private String fechaEnvio;
    private String tipoNotificacion;
    private String estadoNotificacion;
    private String prioridad;

    // Constructor
    public Notificaciones(String mensaje, String fechaEnvio, String tipoNotificacion, String estadoNotificacion, String prioridad) {
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.tipoNotificacion = tipoNotificacion;
        this.estadoNotificacion = estadoNotificacion;
        this.prioridad = prioridad;
    }

    // Métodos getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(String estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    // Método para enviar notificación
    public void enviarNotificacion() {
        System.out.println("Enviando notificación: " + mensaje);
    }
}
