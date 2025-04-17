package proy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HistorialJustificante extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JTextArea justificantesArea;
    private JButton loginButton, solicitaButton, descargaButton, enviarCorreoButton;
    private JLabel mensajeLabel;
    
    private String usuarioRegistrado = "paciente1";
    private String contrasenaRegistrada = "12345";
    private String justificanteActual = "";

    public HistorialJustificante() {
        setTitle("Historial de Justificantes Médicos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel usuarioPanel = new JPanel();
        usuarioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        usuarioPanel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField(27);
        usuarioPanel.add(usuarioField);
        panel.add(usuarioPanel);

        JPanel contrasenaPanel = new JPanel();
        contrasenaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        contrasenaPanel.add(new JLabel("Contraseña:"));
        contrasenaField = new JPasswordField(25);
        contrasenaPanel.add(contrasenaField);
        panel.add(contrasenaPanel);

        loginButton = new JButton("Iniciar Sesión");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Botones centrados
        buttonPanel.add(loginButton);
        panel.add(buttonPanel);

        JPanel justificantePanel = new JPanel();
        justificantePanel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        justificantePanel.add(new JLabel("Justificantes Previos:"));
        justificantesArea = new JTextArea(5, 25);
        justificantesArea.setEditable(false);
        panel.add(new JScrollPane(justificantesArea));

        JPanel buttonActionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        solicitaButton = new JButton("Solicitar Justificante");
        solicitaButton.setEnabled(false);
        buttonActionPanel.add(solicitaButton);

        descargaButton = new JButton("Descargar Justificante");
        descargaButton.setEnabled(false);
        buttonActionPanel.add(descargaButton);
        
        enviarCorreoButton = new JButton("Mandar por Correo");
        enviarCorreoButton.setEnabled(false);
        buttonActionPanel.add(enviarCorreoButton);

        panel.add(buttonActionPanel);

        mensajeLabel = new JLabel("", JLabel.CENTER);
        panel.add(mensajeLabel);
        add(panel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        solicitaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solicitarJustificante();
            }
        });
        descargaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                descargarJustificante();
            }
        });
        enviarCorreoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarPorCorreo();
            }
        });
    }

    private void iniciarSesion() {
        String usuario = usuarioField.getText();
        String contrasena = new String(contrasenaField.getPassword());

        if (usuario.equals(usuarioRegistrado) && contrasena.equals(contrasenaRegistrada)) {
            mensajeLabel.setText("Inicio de sesión exitoso.");
            solicitaButton.setEnabled(true);
            justificantesArea.setText(" Justificante previos: ");
        } else {
            mensajeLabel.setText("Usuario o contraseña incorrectos.");
        }
    }

    private void solicitarJustificante() {
        String motivo = JOptionPane.showInputDialog(this, "Ingrese el motivo del justificante:");
        if (motivo != null && !motivo.trim().isEmpty()) {
            justificanteActual = "Justificante: " + motivo + ": " + fechaActual;
            justificantesArea.append("\n" + justificanteActual);
            mensajeLabel.setText("Justificante solicitado.");
            descargaButton.setEnabled(true);
            enviarCorreoButton.setEnabled(true);
        } else {
            mensajeLabel.setText("Debe ingresar un motivo válido.");
        }
    }

    String fechaActual = "23/03/25";
    private void descargarJustificante() {
        if (!justificanteActual.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Descargando justificante...\n" + justificanteActual + " " + fechaActual);
            mensajeLabel.setText("Justificante descargado.");
        } else {
            mensajeLabel.setText("No hay justificantes para descargar.");
        }
    }
    
    private void enviarPorCorreo() {
        String correo = JOptionPane.showInputDialog(this, "Ingrese el correo del maestro:");
        if (correo != null && !correo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Justificante enviado a: " + correo);
            mensajeLabel.setText("Justificante enviado por correo.");
        } else {
            mensajeLabel.setText("Debe ingresar un correo válido.");
        }
    }

    public static void main(String[] args) {
        new HistorialJustificante().setVisible(true);
    }
}



