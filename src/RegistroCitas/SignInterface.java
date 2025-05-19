package RegistroCitas;

import MenuPrincipal.*;
import User.UserBase;
import User.Usuario;
import Utilidades.ConexionSQLite;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import Expediente.*;

public class SignInterface {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, recoverPasswordButton;
    private UserBase userBase;
    private AppointmentBase registroCitasGlobal;
    private MainMenu MenuPrincipal;

    public static String correoActivo = null; // GUARDAR EL CORREO PARA USO GLOBAL

    public SignInterface(UserBase userBase, AppointmentBase registroCitasGlobal, MainMenu MPrincipal) {
        MenuPrincipal = MPrincipal;
        this.userBase = userBase;
        this.registroCitasGlobal = registroCitasGlobal;

        frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2));

        frame.add(new JLabel("Correo Electrónico:"));
        emailField = new JTextField();
        frame.add(emailField);

        frame.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        loginButton = new JButton("Iniciar Sesión");
        registerButton = new JButton("Registrar");
        recoverPasswordButton = new JButton("Recuperar Contraseña");

        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(recoverPasswordButton);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> openRegisterWindow());
        recoverPasswordButton.addActionListener(e -> openRecoveryWindow());

        frame.setVisible(true);

        // Si el usuario cierra la ventana de inicio de sesión, se regresa al MenuInicio
frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        new MenuInicio(null);
    }
});

    }

    private void login() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionSQLite.conectar()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(frame, "No se pudo conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM base WHERE correo_paciente = ? OR correo_doctor = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean esDoctor = email.equals(rs.getString("correo_doctor"));
                boolean contrasenaCorrecta = (esDoctor && password.equals(rs.getString("contrasena_doctor"))) ||
                                             (!esDoctor && password.equals(rs.getString("contrasena_paciente")));

                if (contrasenaCorrecta) {
                    Usuario usuario;
                    if (esDoctor) {
                        usuario = new Usuario(
                            rs.getString("nombre_doctor"),
                            rs.getString("apellido_doctor"),
                            email,
                            password,
                            rs.getString("telefono_doctor"),
                            rs.getString("direccion_doctor"),
                            rs.getString("ciudad_doctor"),
                            rs.getString("estado_doctor"),
                            rs.getString("cp_doctor"),
                            rs.getString("pais_doctor"),
                            registroCitasGlobal
                        );
                        usuario.setDoctor();
                    } else {
                        usuario = new Usuario(
                            rs.getString("nombre_paciente"),
                            rs.getString("apellido_paciente"),
                            email,
                            password,
                            rs.getString("telefono_paciente"),
                            rs.getString("direccion_paciente"),
                            rs.getString("ciudad_paciente"),
                            rs.getString("estado_paciente"),
                            rs.getString("cp_paciente"),
                            rs.getString("pais_paciente"),
                            registroCitasGlobal
                        );
                    }

                    correoActivo = email; // GUARDAR CORREO PARA USO GLOBAL

                    JOptionPane option = new JOptionPane(
                        esDoctor ? "Inicio de sesión exitoso como doctor." : "Inicio de sesión exitoso como paciente.",
                        JOptionPane.INFORMATION_MESSAGE,
                        JOptionPane.DEFAULT_OPTION
                    );

                    JDialog dialog = option.createDialog("Éxito");
                    dialog.setModal(true);
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true); // Muestra y espera cierre

                    frame.dispose(); // Cerrar login después del mensaje

                    // Abrir el menú correspondiente
                    if (esDoctor) {
                        new DoctorMenuInterface(usuario);
                    } else {
                        new MainMenu(usuario, registroCitasGlobal);
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error en base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegisterWindow() {
        new RegisterInterface(userBase, registroCitasGlobal);
    }

    private void openRecoveryWindow() {
        new PasswordRecoveryInterface(userBase);
    }
}
