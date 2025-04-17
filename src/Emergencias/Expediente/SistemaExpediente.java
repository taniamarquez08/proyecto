package Expediente;
import User.*;

public class SistemaExpediente {
    public Usuario[] usuarios;     // Cambiar a public
    public UsuarioDoctor[] doctores; // Cambiar a public
    ExpedienteNuevo[] expedientes;
    private Notificaciones[] notificaciones;

    @SuppressWarnings("unused")
    private String sintomas;
    @SuppressWarnings("unused")
    private String diagnostico;
    @SuppressWarnings("unused")
    private String tratamiento;
    @SuppressWarnings("unused")
    private String estudios;
    @SuppressWarnings("unused")
    private String medicamento;
    @SuppressWarnings("unused")
    private String fecha;
    @SuppressWarnings("unused")
    private String firma;

    // Constructor
    public SistemaExpediente() {
        this.expedientes = new ExpedienteNuevo[5];
        this.usuarios = new Usuario[5];
        this.doctores = new UsuarioDoctor[5];
        this.notificaciones = new Notificaciones[5];
    }

    // Métodos para agregar usuarios, expedientes, doctores y notificaciones
    public void agregarExpediente(ExpedienteNuevo expediente) {
        for (int i = 0; i < expedientes.length; i++) {
            if (expedientes[i] == null) {
                expedientes[i] = expediente;  // Añade el expediente al primer espacio vacío
                break;
            }
        }
    }

    public void agregarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuario;  // Añadir usuario al primer espacio vacío
                break;
            }
        }
    }

    public void agregarDoctor(UsuarioDoctor doctor) {
        for (int i = 0; i < doctores.length; i++) {
            if (doctores[i] == null) {
                doctores[i] = doctor;  // Añadir doctor al primer espacio vacío
                break;
            }
        }
    }

    public void agregarNotificacion(Notificaciones notificacion) {
        for (int i = 0; i < notificaciones.length; i++) {
            if (notificaciones[i] == null) {
                notificaciones[i] = notificacion;  // Añade la notificación al primer espacio vacío
                break;
            }
        }
    }

    // Métodos para registrar y actualizar los datos del expediente
    public void registrarConsulta(String sintomas, String diagnostico, String tratamiento, String estudios, String medicamento, String fecha, String firma) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.estudios = estudios;
        this.medicamento = medicamento;
        this.fecha = fecha;
        this.firma = firma;
        
        // Crear un nuevo expediente
        ExpedienteNuevo nuevoExpediente = new ExpedienteNuevo(0, sintomas, diagnostico, tratamiento, estudios, firma, firma, null);
        agregarExpediente(nuevoExpediente);
        
        // Crear y agregar una nueva notificación
        Notificaciones notificacion = new Notificaciones("Consulta registrada", fecha, "Consulta médica", "Pendiente", "Alta");
        agregarNotificacion(notificacion);
    }

    // Método para obtener el expediente por número
    public ExpedienteNuevo obtenerExpediente(int numExpediente) {
        for (ExpedienteNuevo expediente : expedientes) {
            if (expediente != null && expediente.getNumExpediente() == numExpediente) {
                return expediente;
            }
        }
        return null;  // Si no se encuentra el expediente
    }

    // Método para autenticar un doctor
    public boolean autenticarDoctor(String doctorID, String doctorCodigoSeguridad) {
        // Revisa si el doctor existe en el sistema
        for (UsuarioDoctor doctor : doctores) {
            if (doctor != null && doctor.verificarUsuario(doctorID, doctorCodigoSeguridad)) {
                return true;
            }
        }
        return false;  // Si el doctor no se encuentra o los datos no coinciden
    }

    // Método para actualizar el expediente
    public void actualizarExpediente(int numExpediente, String sintomas, String diagnostico, String tratamiento, String estudios, String medicamento, String fecha) {
        ExpedienteNuevo expediente = obtenerExpediente(numExpediente);
        if (expediente != null) {
            expediente.setSintomas(sintomas);
            expediente.setDiagnostico(diagnostico);
            expediente.setTratamiento(tratamiento);
            expediente.setEstudios(estudios);
            // expediente.setFechaModificacion(fecha);  // Fecha de modificación del expediente
            // Aquí también podrías actualizar las notificaciones relacionadas con la modificación
        }
    }

    // Método para mostrar los expedientes
    public void mostrarExpedientes() {
        for (ExpedienteNuevo expediente : expedientes) {
            if (expediente != null) {
                System.out.println(expediente.getNombre() + " - " + expediente.getDiagnostico());
            }
        }
    }
}
