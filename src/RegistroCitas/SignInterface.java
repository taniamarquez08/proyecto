package RegistroCitas;
import MenuPrincipal.*;
import User.UserBase;
import User.Usuario;
import java.awt.*;
import javax.swing.*;

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
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        
        Usuario user = userBase.findUserFromEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");

            
            frame.dispose();
            
            MenuPrincipal.UpdateUserDisplayFromUser(user);

            if (user.getDoctor()) {
                new DoctorInterface(user);
            } else {

                new PatientInterface(user, new AppointmentInterface(user));

            }
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
        }
    }

    private void openRegisterWindow() {
        new RegisterInterface(userBase, registroCitasGlobal);
    }

    private void openRecoveryWindow() {
        new PasswordRecoveryInterface(userBase);
    }

}
