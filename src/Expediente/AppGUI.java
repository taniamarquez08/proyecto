package Expediente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import Expediente.*;

public class AppGUI {
    private JFrame frame;
    private JTextField txtNombre, txtApellido, txtSintomas, txtDiagnostico, txtReceta;
    private JTextArea txtAreaExpedientes;
    private SistemaExpediente sistema;  // Objeto del sistema de expedientes
    private JFrame ventanaAutenticacion;
    private JLabel lblFechaConsulta;
    
    // Datos del doctor para la autenticación (almacenados en la misma clase App)
    private String doctorID = "doctor123";  // El ID del doctor
    private String doctorCodigoSeguridad = "seguridad456";  // El código de seguridad del doctor

    public AppGUI(SistemaExpediente sistema) {
        this.sistema = sistema;
        // Llamamos a la ventana de autenticación para el doctor -- Quienes llaman jaja?
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
    // Aqui si esta padre la implementacion, el problema es que App.java ya ni sirve :sob:
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

    private void mostrarVentanaExpediente() {
        SwingUtilities.invokeLater(() -> {
            VentanaExpedienteMedico ventana = new VentanaExpedienteMedico(sistema);
            ventana.setVisible(true);
        });
    }
    

    // Main para ejecutar la aplicación
    public static void main(String[] args) {
        SistemaExpediente sistema = new SistemaExpediente();  // Instanciamos SistemaExpediente
        new AppGUI(sistema);  //Creamos la interfaz gráfica pasándole el sistema
    }
}