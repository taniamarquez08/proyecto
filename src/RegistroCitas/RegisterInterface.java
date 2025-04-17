package RegistroCitas;
import User.UserBase;
import User.Usuario;
import java.awt.*;
import javax.swing.*;

class RegisterInterface {
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

        Usuario newUser = new Usuario(
            nombreField.getText(), apellidoField.getText(), emailField.getText(),
            password, telefonoField.getText(), direccionField.getText(),
            ciudadField.getText(), estadoField.getText(), codigoPostalField.getText(),
            paisField.getText(), registroCitasGlobal
        );

        if (doctorCheckBox.isSelected()) {
            newUser.setDoctor();
        }

        userBase.addUser(newUser);
        JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente");
        frame.dispose();
    }
}
