package MenuPrincipal;

import Expediente.*;
import RegistroCitas.*;
import User.*;
import Tratamiento.*;
import Historial.*;
import Emergencias.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuInicio {
    private JFrame frame;
    private JLabel titleLabel, imageLabel;
    private JButton botonSesion, botonEmergencias, botonOpciones;
    private Usuario usuario;

    // Constructor
    public MenuInicio(Usuario U) {
        this.usuario = U;

        // Crear la ventana
        frame = new JFrame("MedConnect - MenuInicio");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setLayout(new BorderLayout());

        // ----------------------------------------------------
        // 1) Panel superior (topBar) con FlowLayout para poner el botón "Opciones" a la izquierda
        // ----------------------------------------------------
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonOpciones = new JButton("Opciones");
        botonOpciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OptionsMenu(usuario);
            }
        });
        topBar.add(botonOpciones);
        frame.add(topBar, BorderLayout.NORTH);

        // ----------------------------------------------------
        // 2) Panel central (centerPanel) con GridBagLayout para centrar título e imagen
        // ----------------------------------------------------
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE); // Fondo blanco
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // Márgenes
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;

        // Título "MedConnect" en azul rey y más grande
        titleLabel = new JLabel("MedConnect", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        titleLabel.setForeground(new Color(65,105,225)); // Azul rey
        c.gridy = 0;
        centerPanel.add(titleLabel, c);

        // Cargar y escalar la imagen (300x225)
        ImageIcon originalIcon = null;
        try {
            originalIcon = new ImageIcon(getClass().getResource("/MenuPrincipal/background.jpeg"));
        } catch(Exception e) {
            System.out.println("No se encontró la imagen: " + e.getMessage());
        }
        if (originalIcon != null && originalIcon.getIconWidth() > 0) {
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(300, 225, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel = new JLabel(scaledIcon);
        } else {
            imageLabel = new JLabel("Imagen no encontrada", SwingConstants.CENTER);
        }
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridy = 1;
        centerPanel.add(imageLabel, c);

        frame.add(centerPanel, BorderLayout.CENTER);

        // ----------------------------------------------------
        // 3) Panel inferior (bottomPanel) con FlowLayout para los botones
        // ----------------------------------------------------
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setBackground(Color.WHITE);

        botonSesion = new JButton("Iniciar Sesión");
        botonEmergencias = new JButton("Emergencia");
        botonEmergencias.setBackground(Color.RED);
        botonEmergencias.setForeground(Color.WHITE);

        // Acción para abrir SignInterface cuando se presione "Iniciar Sesión"
        botonSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear instancias de UserBase y AppointmentBase
                UserBase ub = new UserBase();
                AppointmentBase ab = new AppointmentBase();
                // Abrir SignInterface con null para el MainMenu (si no lo necesitas)
                new SignInterface(ub, ab, null);
            }
        });

        // Acción para abrir la ventana de Emergency cuando se presione "Emergencia"
        botonEmergencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Emergency().setVisible(true);
            }
        });

        bottomPanel.add(botonSesion);
        bottomPanel.add(botonEmergencias);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Método main para probar MenuInicio de forma independiente
    public static void main(String[] args) {
        // Se crea un AppointmentBase para el usuario dummy
        AppointmentBase dummyAppointment = new AppointmentBase();
        
        // Ajusta el constructor de Usuario para el onceavo parámetro (AppointmentBase)
        Usuario dummyUser = new Usuario(
            "Default", "", "", "", "", "", "", "", "", "",
            dummyAppointment // <--- Pasar un AppointmentBase, no un String
        );

        new MenuInicio(dummyUser);
    }
}
