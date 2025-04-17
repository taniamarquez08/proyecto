package RegistroCitas;
import User.Usuario;
import java.awt.*;
import javax.swing.*;

class ChangePasswordInterface {
    private JFrame frame;
    private JPasswordField newPasswordField, confirmPasswordField;
    private JButton changePasswordButton;
    private Usuario user;

    public ChangePasswordInterface(Usuario user) {
        this.user = user;
        frame = new JFrame("Cambiar Contraseña");
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("Nueva Contraseña:"));
        newPasswordField = new JPasswordField();
        frame.add(newPasswordField);

        frame.add(new JLabel("Confirmar Contraseña:"));
        confirmPasswordField = new JPasswordField();
        frame.add(confirmPasswordField);

        changePasswordButton = new JButton("Cambiar Contraseña");
        frame.add(changePasswordButton);

        changePasswordButton.addActionListener(e -> changePassword());

        frame.setVisible(true);
    }

    private void changePassword() {
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (newPassword.equals(confirmPassword)) {
            user.setPassword(newPassword);
            JOptionPane.showMessageDialog(frame, "Contraseña cambiada exitosamente");
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden");
        }
    }
}

