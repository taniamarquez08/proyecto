package RegistroCitas;

import User.UserBase;
import User.Usuario;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import Utilidades.ConexionSQLite;

public class RegisterInterface {
    private JFrame frame;
    private JTextField nombreField, apellidoField, emailField, telefonoField, direccionField, ciudadField, estadoField, codigoPostalField, paisField;
    private JPasswordField passwordField;
    private JCheckBox doctorCheckBox;
    private JButton registerButton;
    private UserBase userBase;
    private AppointmentBase registroCitasGlobal;

    public RegisterInterface(UserBase userBase, AppointmentBase registroCitasGlobal) {
        this.userBase = userBase;
        this.registroCitasGlobal = registroCitasGlobal;
        frame = new JFrame("Registro de Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new GridLayout(15, 2));

        frame.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        frame.add(nombreField);

        frame.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        frame.add(apellidoField);

        frame.add(new JLabel("Correo Electrónico:"));
        emailField = new JTextField();
        frame.add(emailField);

        frame.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        frame.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        frame.add(telefonoField);

        frame.add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        frame.add(direccionField);

        frame.add(new JLabel("Ciudad:"));
        ciudadField = new JTextField();
        frame.add(ciudadField);

        frame.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        frame.add(estadoField);

        frame.add(new JLabel("Código Postal:"));
        codigoPostalField = new JTextField();
        frame.add(codigoPostalField);

        frame.add(new JLabel("País:"));
        paisField = new JTextField();
        frame.add(paisField);

        frame.add(new JLabel("¿Es doctor?"));
        doctorCheckBox = new JCheckBox();
        frame.add(doctorCheckBox);

        registerButton = new JButton("Registrar");
        frame.add(registerButton);

        registerButton.addActionListener(e -> registerUser());

        frame.setVisible(true);
    }

    private void registerUser() {
        JTextField[] fields = {
            nombreField, apellidoField, emailField, telefonoField,
            direccionField, ciudadField, estadoField, codigoPostalField, paisField
        };
        String password = new String(passwordField.getPassword()).trim();
        boolean allFilled = true;

        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                allFilled = false;
                break;
            }
        }

        if (password.isEmpty()) {
            allFilled = false;
        }

        if (!allFilled) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionSQLite.conectar()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(frame, "No se pudo conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean esDoctor = doctorCheckBox.isSelected();

            String sql;
            if (esDoctor) {
                sql = "INSERT INTO base (" +
                        "nombre_doctor, apellido_doctor, correo_doctor, contrasena_doctor, telefono_doctor, direccion_doctor, ciudad_doctor, estado_doctor, cp_doctor, pais_doctor, " +
                        "nombre_paciente, apellido_paciente, correo_paciente, contrasena_paciente, telefono_paciente, direccion_paciente, ciudad_paciente, estado_paciente, cp_paciente, pais_paciente" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '', '', '', '', '', '', '', '', '', '')";
            } else {
                sql = "INSERT INTO base (" +
                        "nombre_doctor, apellido_doctor, correo_doctor, contrasena_doctor, telefono_doctor, direccion_doctor, ciudad_doctor, estado_doctor, cp_doctor, pais_doctor, " +
                        "nombre_paciente, apellido_paciente, correo_paciente, contrasena_paciente, telefono_paciente, direccion_paciente, ciudad_paciente, estado_paciente, cp_paciente, pais_paciente" +
                        ") VALUES ('', '', '', '', '', '', '', '', '', '', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            if (esDoctor) {
                stmt.setString(1, nombreField.getText());
                stmt.setString(2, apellidoField.getText());
                stmt.setString(3, emailField.getText());
                stmt.setString(4, password);
                stmt.setString(5, telefonoField.getText());
                stmt.setString(6, direccionField.getText());
                stmt.setString(7, ciudadField.getText());
                stmt.setString(8, estadoField.getText());
                stmt.setString(9, codigoPostalField.getText());
                stmt.setString(10, paisField.getText());
            } else {
                stmt.setString(1, nombreField.getText());
                stmt.setString(2, apellidoField.getText());
                stmt.setString(3, emailField.getText());
                stmt.setString(4, password);
                stmt.setString(5, telefonoField.getText());
                stmt.setString(6, direccionField.getText());
                stmt.setString(7, ciudadField.getText());
                stmt.setString(8, estadoField.getText());
                stmt.setString(9, codigoPostalField.getText());
                stmt.setString(10, paisField.getText());
            }

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente en la base de datos.");
            frame.dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
