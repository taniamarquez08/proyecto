package Expediente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AppGUI {
    private JFrame frame;
    private JTextField txtNombre, txtApellido, txtSintomas, txtDiagnostico, txtReceta;
    private JTextArea txtAreaExpedientes;
    private SistemaExpediente sistema;  // Objeto del sistema de expedientes
    private JFrame ventanaAutenticacion;
    
    // Datos del doctor para la autenticación (almacenados en la misma clase App)
    private String doctorID = "doctor123";  // El ID del doctor
    private String doctorCodigoSeguridad = "seguridad456";  // El código de seguridad del doctor

    public AppGUI(SistemaExpediente sistema) {
        this.sistema = sistema;
        // Llamamos a la ventana de autenticación para el doctor
        mostrarVentanaAutenticacion();
    }

    // Método para mostrar la ventana de autenticación
    private void mostrarVentanaAutenticacion() {
        ventanaAutenticacion = new JFrame("Autenticación del Médico");
        ventanaAutenticacion.setSize(300, 200);
        ventanaAutenticacion.setLayout(new GridLayout(3, 2));

        JLabel lblIDDoctor = new JLabel("ID del Doctor:");
        JTextField txtIDDoctor = new JTextField();
        JLabel lblCodigoSeguridad = new JLabel("Código de Seguridad:");
        JTextField txtCodigoSeguridad = new JTextField();

        JButton btnAutenticar = new JButton("Autenticar");
        btnAutenticar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarDoctor(txtIDDoctor.getText(), txtCodigoSeguridad.getText(), ventanaAutenticacion);
            }
        });

        ventanaAutenticacion.add(lblIDDoctor);
        ventanaAutenticacion.add(txtIDDoctor);
        ventanaAutenticacion.add(lblCodigoSeguridad);
        ventanaAutenticacion.add(txtCodigoSeguridad);
        ventanaAutenticacion.add(btnAutenticar);

        ventanaAutenticacion.setVisible(true);
    }

    // Método para autenticar al doctor usando los datos almacenados en App.java
    private void autenticarDoctor(String idDoctor, String codigoSeguridad, JFrame ventanaAutenticacion) {
        // Verificar si el doctor existe y la autenticación es correcta usando los datos predefinidos
        if (doctorID.equals(idDoctor) && doctorCodigoSeguridad.equals(codigoSeguridad)) {
            JOptionPane.showMessageDialog(ventanaAutenticacion, "Autenticación exitosa.");
            ventanaAutenticacion.dispose();  // Cerrar la ventana de autenticación
            mostrarVentanaExpediente();  // Abrir la ventana de expediente una vez autenticado
        } else {
            JOptionPane.showMessageDialog(ventanaAutenticacion, "Autenticación fallida. Verifique los datos.");
        }
    }

    // Método para mostrar la ventana principal de los expedientes (después de autenticación)
    private void mostrarVentanaExpediente() {
        frame = new JFrame("Sistema de Gestión de Expedientes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel para ingresar datos del expediente
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(6, 2));

        JLabel lblNombre = new JLabel("Nombre del Paciente:");
        txtNombre = new JTextField();
        panelInput.add(lblNombre);
        panelInput.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();
        panelInput.add(lblApellido);
        panelInput.add(txtApellido);

        JLabel lblSintomas = new JLabel("Síntomas:");
        txtSintomas = new JTextField();
        panelInput.add(lblSintomas);
        panelInput.add(txtSintomas);

        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        txtDiagnostico = new JTextField();
        panelInput.add(lblDiagnostico);
        panelInput.add(txtDiagnostico);

        JLabel lblReceta = new JLabel("Receta:");
        txtReceta = new JTextField();
        panelInput.add(lblReceta);
        panelInput.add(txtReceta);

        // Botón para actualizar expediente
        JButton btnActualizarExpediente = new JButton("Actualizar Expediente");
        btnActualizarExpediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarConsulta();  // Actualizar expediente
            }
        });

        // Área de texto para mostrar expedientes
        txtAreaExpedientes = new JTextArea();
        txtAreaExpedientes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaExpedientes);

        // Añadir componentes al frame
        JPanel panelButtons = new JPanel();
        panelButtons.add(btnActualizarExpediente);

        frame.add(panelInput, BorderLayout.NORTH);
        frame.add(panelButtons, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Método para registrar consulta y actualizar expediente
    private void registrarConsulta() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String sintomas = txtSintomas.getText();
        String diagnostico = txtDiagnostico.getText();
        String receta = txtReceta.getText();

        // Crear un nuevo expediente con los datos proporcionados
        ExpedienteNuevo expediente = new ExpedienteNuevo(1, sintomas, diagnostico, "Tratamiento", "Estudios", 
            nombre + " " + apellido, receta, new Date());  // Registrar la fecha de la consulta

        // Agregar el expediente al sistema
        sistema.agregarExpediente(expediente);

        // Cerrar la ventana principal automáticamente después de guardar el expediente
        frame.dispose();  // Cierra la ventana principal

        // Mostrar los datos en una ventana emergente
        mostrarVentanaExpedienteGuardado(expediente);

        // Llamar a mostrar expedientes para actualizar el área de texto con los nuevos datos
        mostrarExpedientes();  // Llamar al método para mostrar todos los expedientes actualizados
    }

    // Método para mostrar una ventana con el expediente guardado
    private void mostrarVentanaExpedienteGuardado(ExpedienteNuevo expediente) {
        String expedienteGuardado = "Expediente Guardado Exitosamente:\n";
        expedienteGuardado += "Número de Expediente: " + expediente.getNumExpediente() + "\n";
        expedienteGuardado += "Nombre del Paciente: " + expediente.getNombre() + "\n";
        expedienteGuardado += "Diagnóstico: " + expediente.getDiagnostico() + "\n";
        expedienteGuardado += "Síntomas: " + expediente.getSintomas() + "\n";
        expedienteGuardado += "Receta: " + expediente.getReceta() + "\n";
        expedienteGuardado += "Fecha de Consulta: " + expediente.getFechaConsulta() + "\n";

        // Mostrar un mensaje de éxito con los detalles del expediente
        JOptionPane.showMessageDialog(null, expedienteGuardado, "Expediente Guardado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para mostrar expedientes
    private void mostrarExpedientes() {
        StringBuilder sb = new StringBuilder();
        for (ExpedienteNuevo expediente : sistema.expedientes) {
            if (expediente != null) {
                sb.append("Expediente Número: ").append(expediente.getNumExpediente()).append("\n");
                sb.append("Nombre: ").append(expediente.getNombre()).append("\n");
                sb.append("Diagnóstico: ").append(expediente.getDiagnostico()).append("\n");
                sb.append("Sintomas: ").append(expediente.getSintomas()).append("\n");
                sb.append("Receta: ").append(expediente.getReceta()).append("\n");
                sb.append("Fecha de Consulta: ").append(expediente.getFechaConsulta()).append("\n");
                sb.append("---------------------------------------------------\n");
            }
        }
        txtAreaExpedientes.setText(sb.toString());  // Actualiza el área de texto con todos los expedientes
    }

    // Main para ejecutar la aplicación
    public static void main(String[] args) {
        SistemaExpediente sistema = new SistemaExpediente();  // Instanciamos SistemaExpediente
        new AppGUI(sistema);  // Creamos la interfaz gráfica pasándole el sistema
    }
}

