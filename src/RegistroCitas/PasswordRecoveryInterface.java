package RegistroCitas;
import User.UserBase;
import User.Usuario;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

class PasswordRecoveryInterface {
    private JFrame frame;
    private JTextField emailField;
    private JButton sendCodeButton;
    private UserBase userBase;
    private int verificationCode;

    public PasswordRecoveryInterface(UserBase userBase) {
        this.userBase = userBase;
        frame = new JFrame("Recuperar Contraseña");
        frame.setSize(400, 150);
        frame.setLayout(new GridLayout(2, 2));
        
        frame.add(new JLabel("Ingrese su correo:"));
        emailField = new JTextField();
        frame.add(emailField);
        
        sendCodeButton = new JButton("Enviar Código");
        frame.add(sendCodeButton);
        
        sendCodeButton.addActionListener(e -> sendVerificationCode());
        
        frame.setVisible(true);
    }

    private void sendVerificationCode() {
        String email = emailField.getText();
        Usuario user = userBase.findUserFromEmail(email);
        if (user != null) {
            verificationCode = new Random().nextInt(9000) + 1000; // Código de 4 dígitos
            JOptionPane.showMessageDialog(frame, "Código enviado: " + verificationCode);
            frame.dispose();
            new CodeVerificationInterface(user, verificationCode);
        } else {
            JOptionPane.showMessageDialog(frame, "Correo no registrado");
        }
    }
}
