package proy;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Emergency extends JFrame {
    private JButton findHospitalButton;
    private JButton findContactButton;
    private JButton emergencyButton;

    public Emergency() {
        setTitle("Sistema de Emergencias / Paramédicos no encontrados");
        setSize(243, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Hospital
        findHospitalButton = new JButton("      Buscar Hospital Cercano     ");
        findHospitalButton.setPreferredSize(new Dimension(1000, 300));

        // Contacto
        findContactButton = new JButton("Buscar Contacto de Emergencia");
        findContactButton.setPreferredSize(new Dimension(1000, 300)); 

        // Emergencia
        emergencyButton = new JButton("              EMERGENCIA               ");
        emergencyButton.setPreferredSize(new Dimension(500, 300)); 
        emergencyButton.setBackground(new Color(255, 51, 51));
        emergencyButton.setOpaque(true);
        emergencyButton.setForeground(Color.RED);

        findHospitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Buscando hospitales cercanos...");
            }
        });

        findContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Buscando contactos de emergencia...");
            }
        });

        emergencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Enviando localizacion...\n Los paramédicos llegaran pronto");
            }
        });

        panel.add(findHospitalButton);
        panel.add(findContactButton);
        panel.add(emergencyButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Emergency().setVisible(true);
        });
    }
}
