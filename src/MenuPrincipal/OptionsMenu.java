package MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import User.Usuario;

public class OptionsMenu {
    private JFrame frame;
    private Usuario usuario;
    private boolean modoOscuro = false;
    
    public OptionsMenu(Usuario usuario) {
        this.usuario = usuario;
        frame = new JFrame("Opciones");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        
        JButton btnCuenta = new JButton("Cuenta");
        JButton btnAyuda = new JButton("Ayuda");
        JButton btnConfiguracion = new JButton("Configuración");
        
        btnCuenta.addActionListener(e -> abrirCuenta());
        btnAyuda.addActionListener(e -> abrirAyuda());
        btnConfiguracion.addActionListener(e -> abrirConfiguracion());
        
        panel.add(btnCuenta);
        panel.add(btnAyuda);
        panel.add(btnConfiguracion);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private void abrirCuenta() {
        JFrame cuentaFrame = new JFrame("Cuenta");
        cuentaFrame.setSize(300, 250);
        cuentaFrame.setLayout(new GridLayout(6, 2));
        
        JTextField nombreField = new JTextField(usuario.getNombre());
        JTextField apellidoField = new JTextField(usuario.getApellido());
        JTextField emailField = new JTextField(usuario.getEmail());
        JTextField telefonoField = new JTextField(usuario.getTelefono());
        
        JButton guardarBtn = new JButton("Guardar");
        guardarBtn.addActionListener(e -> {
            usuario.setNombre(nombreField.getText());
            usuario.setApellido(apellidoField.getText());
            usuario.setEmail(emailField.getText());
            usuario.setTelefono(telefonoField.getText());
            JOptionPane.showMessageDialog(cuentaFrame, "Datos actualizados");
        });
        
        cuentaFrame.add(new JLabel("Nombre:")); cuentaFrame.add(nombreField);
        cuentaFrame.add(new JLabel("Apellido:")); cuentaFrame.add(apellidoField);
        cuentaFrame.add(new JLabel("Email:")); cuentaFrame.add(emailField);
        cuentaFrame.add(new JLabel("Teléfono:")); cuentaFrame.add(telefonoField);
        cuentaFrame.add(guardarBtn);
        
        cuentaFrame.setVisible(true);
    }
    
    private void abrirAyuda() {
        JFrame ayudaFrame = new JFrame("Ayuda");
        ayudaFrame.setSize(300, 200);
        ayudaFrame.setLayout(new GridLayout(3, 1));
        
        JLabel infoLabel = new JLabel("Para soporte, contacta: support@medconnect.com");
        JButton btnSoporte = new JButton("Crear mensaje de soporte");
        btnSoporte.addActionListener(e -> mostrarFormularioSoporte());
        
        ayudaFrame.add(infoLabel);
        ayudaFrame.add(btnSoporte);
        ayudaFrame.setVisible(true);
    }
    
    private void mostrarFormularioSoporte() {
        JFrame soporteFrame = new JFrame("Mensaje de Soporte");
        soporteFrame.setSize(300, 200);
        soporteFrame.setLayout(new GridLayout(3, 1));
        
        JTextField tituloField = new JTextField("Título del problema");
        JTextArea descripcionField = new JTextArea("Describe tu problema aquí");
        JButton enviarBtn = new JButton("Enviar");
        
        soporteFrame.add(tituloField);
        soporteFrame.add(descripcionField);
        soporteFrame.add(enviarBtn);
        
        soporteFrame.setVisible(true);
    }
    
    private void abrirConfiguracion() {
        JFrame configFrame = new JFrame("Configuración");
        configFrame.setSize(300, 150);
        
        JCheckBox modoOscuroCheck = new JCheckBox("Modo Oscuro", modoOscuro);
        modoOscuroCheck.addActionListener(e -> {
            modoOscuro = modoOscuroCheck.isSelected();
            JOptionPane.showMessageDialog(configFrame, "Modo Oscuro: " + (modoOscuro ? "Activado" : "Desactivado"));
        });
        
        configFrame.add(modoOscuroCheck);
        configFrame.setVisible(true);
    }
}