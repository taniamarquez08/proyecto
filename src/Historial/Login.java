package Historial;

/**
 * @(#)Login.java
 *
 *
 * @author 
 * @version 1.00 2025/3/16
 */


 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 
 public class Login extends JFrame {
     private JTextField usernameField;
     private JPasswordField passwordField;
     private JButton loginButton;
 
     public Login() {
         super("Login");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(300, 150);
         setLayout(new GridLayout(3, 2, 5, 5));
 
         add(new JLabel("Usuario:"));
         usernameField = new JTextField();
         add(usernameField);
 
         add(new JLabel("Contrase�a:"));
         passwordField = new JPasswordField();
         add(passwordField);
 
         loginButton = new JButton("Ingresar");
         loginButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 checkCredentials(usernameField.getText(), new String(passwordField.getPassword()));
             }
         });
         add(loginButton);
 
         setLocationRelativeTo(null);
         this.setVisible(true);

     }
 
     private void checkCredentials(String username, String password) {
         if ("Sistemas".equals(username) && "12345".equals(password)) {
             JOptionPane.showMessageDialog(this, "Bienvenido!");
             dispose(); // Cierra la ventana de login
             new InterfazPaciente().setVisible(true); // Abre la ventana principal
         } else {
             JOptionPane.showMessageDialog(this, "Usuario o contrase�a incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
             usernameField.setText("");
             passwordField.setText("");
         }
     }
 
     public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> new Login().setVisible(true));
     }
 }
