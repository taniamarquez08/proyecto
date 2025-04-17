import Expediente.*;
import MenuPrincipal.MenuInicio;
import RegistroCitas.*;
import User.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando la app...");

        // Abel
        UserBase GlobalUserBase = new UserBase();
        AppointmentBase registroCitasGlobal = new AppointmentBase();

        // Tania
        // Crear una instancia de SistemaExpediente
        SistemaExpediente sistemaExp = new SistemaExpediente();

        // Crear algunos usuarios
        Usuario usuario1 = new Usuario("Juan", "Pérez", "juan@mail.com", "12345", "555-1234",
            "Calle Falsa 123", "Ciudad", "Estado", "12345", "Pais", new AppointmentBase());
        Usuario usuario2 = new Usuario("Maria", "Gómez", "maria@mail.com", "password123", "555-5678",
            "Avenida Siempre Viva 456", "Ciudad", "Estado", "67890", "Pais", new AppointmentBase());

        // Crear algunos doctores
        UsuarioDoctor doctor1 = new UsuarioDoctor("Dr. Pedro", "doctor123", "D001", "1234");
        UsuarioDoctor doctor2 = new UsuarioDoctor("Dr. Laura", "doctor456", "D002", "5678");

        // Agregar usuarios y doctores al sistema
        sistemaExp.agregarUsuario(usuario1);
        sistemaExp.agregarUsuario(usuario2);
        sistemaExp.agregarDoctor(doctor1);
        sistemaExp.agregarDoctor(doctor2);

        System.out.println("Usuarios y doctores creados y agregados al sistema.");

        // Crear y agregar un expediente
        ExpedienteNuevo expediente1 = new ExpedienteNuevo(1, "Dolor de cabeza", "Migraña",
            "Ibuprofeno", "Tomografía, análisis de sangre", null, null, null);
        sistemaExp.agregarExpediente(expediente1);
        System.out.println("Expediente agregado: " + expediente1.getNumExpediente());

        // Crear y agregar una notificación
        Notificaciones notificacion1 = new Notificaciones("Consulta registrada para el paciente Juan Pérez", 
            "2025-03-16", "Consulta médica", "Pendiente", "Alta");
        sistemaExp.agregarNotificacion(notificacion1);
        System.out.println("Notificación agregada: " + notificacion1.getMensaje());

        // Registrar una nueva consulta
        sistemaExp.registrarConsulta("Dolor abdominal", "Gastritis", "Omeprazol",
            "Ultrasonido, análisis de sangre", "Omeprazol", "2025-03-16", "FirmaDelDoctor");
        System.out.println("Consulta registrada.");

        // Por último, abre únicamente la ventana de MenuInicio:
        System.out.println("Abriendo MenuInicio...");
        new MenuInicio(new Usuario("Default", "", "", "", "", "", "", "", "", "", registroCitasGlobal));

        
    }
}
