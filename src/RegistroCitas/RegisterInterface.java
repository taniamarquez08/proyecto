package RegistroCitas;
import User.UserBase;
import User.Usuario;
import java.awt.*;
import javax.swing.*;
import java.sql.*; // NUEVO BLOQUE
import Utilidades.ConexionSQLite; // NUEVO BLOQUE

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

        registerButton.addActionListener(e -> registerUser()); // LÍNEA MODIFICADA para leer los datos

        frame.setVisible(true);
    }

    private void registerUser() {
        JTextField[] fields = {nombreField, apellidoField, emailField, telefonoField, direccionField, ciudadField, estadoField, codigoPostalField, paisField};
        String password = new String(passwordField.getPassword());
        boolean allFilled = true;

        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                allFilled = false;
                break;
            }
        }
        
        if (password.trim().isEmpty()) {
            allFilled = false;
        }

        if (!allFilled) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
          // NUEVO BLOQUE: GUARDAR EN LA BASE DE DATOS
          try (Connection conn = ConexionSQLite.conectar()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(frame, "No se pudo conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO base (nombre, apellido, correo_paciente, correo_doctor, contrasena_paciente, contrasena_doctor, telefono, direccion, ciudad, estado, cp, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            boolean esDoctor = doctorCheckBox.isSelected(); // NUEVO BLOQUE
            stmt.setString(1, nombreField.getText()); // NUEVO BLOQUE
            stmt.setString(2, apellidoField.getText()); // NUEVO BLOQUE

            if (esDoctor) {
                stmt.setString(3, ""); // correo_paciente
                stmt.setString(4, emailField.getText()); // correo_doctor
                stmt.setString(5, ""); // contrasena_paciente
                stmt.setString(6, password); // contrasena_doctor
            } else {
                stmt.setString(3, emailField.getText()); // correo_paciente
                stmt.setString(4, ""); // correo_doctor
                stmt.setString(5, password); // contrasena_paciente
                stmt.setString(6, ""); // contrasena_doctor
            }
            

            stmt.setString(7, telefonoField.getText());
            stmt.setString(8, direccionField.getText());
            stmt.setString(9, ciudadField.getText());
            stmt.setString(10, estadoField.getText());
            stmt.setString(11, codigoPostalField.getText());
            stmt.setString(12, paisField.getText());



            stmt.executeUpdate(); // NUEVO BLOQUE
            JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente en la base de datos."); // NUEVO BLOQUE
            frame.dispose(); // NUEVO BLOQUE

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // NUEVO BLOQUE
        }
        // fin de los nuevos bloques 

        //esto creo que ya no se usa 
      //  Usuario newUser = new Usuario(
        //    nombreField.getText(), apellidoField.getText(), emailField.getText(),
          //  password, telefonoField.getText(), direccionField.getText(),
            //ciudadField.getText(), estadoField.getText(), codigoPostalField.getText(),
            //paisField.getText(), registroCitasGlobal
     //   );

       // if (doctorCheckBox.isSelected()) {
         //   newUser.setDoctor();
        //}

       // userBase.addUser(newUser);
        //JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente");
       // frame.dispose();
    }
}
