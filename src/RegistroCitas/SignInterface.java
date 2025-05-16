package RegistroCitas;

import MenuPrincipal.*;
import User.UserBase;
import User.Usuario;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import Utilidades.ConexionSQLite;

public class SignInterface {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, recoverPasswordButton;
    private UserBase userBase;
    private AppointmentBase registroCitasGlobal;
    private MainMenu MenuPrincipal;

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
                String correoPaciente = rs.getString("correo_paciente");
                String correoDoctor = rs.getString("correo_doctor");
                String contrasenaPaciente = rs.getString("contrasena_paciente");
                String contrasenaDoctor = rs.getString("contrasena_doctor");

                boolean esDoctor = correoDoctor != null && correoDoctor.equals(email);
                boolean contrasenaCorrecta = (esDoctor && password.equals(contrasenaDoctor)) ||
                                             (!esDoctor && password.equals(contrasenaPaciente));

                if (contrasenaCorrecta) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String telefono = rs.getString("telefono");
                    String direccion = rs.getString("direccion");
                    String ciudad = rs.getString("ciudad");
                    String estado = rs.getString("estado");
                    String cp = rs.getString("cp");
                    String pais = rs.getString("pais");

                    Usuario usuario = new Usuario(
                        nombre, apellido, email, password,
                        telefono, direccion, ciudad, estado, cp, pais,
                        registroCitasGlobal
                    );

                    if (esDoctor) {
                        usuario.setDoctor();
                        frame.dispose();
                        JOptionPane option = new JOptionPane(
                            "Inicio de sesión exitoso como doctor.",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.DEFAULT_OPTION
                        );
                        JDialog dialog = option.createDialog("Éxito");
                        dialog.setModal(true);
                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                        new DoctorMenuInterface();
                    } else {
                        frame.dispose();
                        JOptionPane option = new JOptionPane(
                            "Inicio de sesión exitoso como paciente.",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.DEFAULT_OPTION
                        );
                        JDialog dialog = option.createDialog("Éxito");
                        dialog.setModal(true);
                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                        new MainMenu(usuario);
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
