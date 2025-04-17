package RegistroCitas;
import MenuPrincipal.*;
import User.UserBase;
import User.Usuario;
import java.awt.*;
import java.util.Random;
import javax.swing.*;


class CodeVerificationInterface {
    private JFrame frame;
    private JTextField codeField;
    private JButton verifyButton;
    private Usuario user;
    private int correctCode;

    public CodeVerificationInterface(Usuario user, int correctCode) {
        this.user = user;
        this.correctCode = correctCode;
        frame = new JFrame("Verificar Código");
        frame.setSize(400, 150);
        frame.setLayout(new GridLayout(2, 2));

        frame.add(new JLabel("Ingrese el código recibido:"));
        codeField = new JTextField();
        frame.add(codeField);

        verifyButton = new JButton("Verificar");
        frame.add(verifyButton);

        verifyButton.addActionListener(e -> verifyCode());

        frame.setVisible(true);
    }

    private void verifyCode() {
        int enteredCode = Integer.parseInt(codeField.getText());
        if (enteredCode == correctCode) {
            frame.dispose();
            new ChangePasswordInterface(user);
        } else {
            JOptionPane.showMessageDialog(frame, "Código incorrecto");
        }
    }
}