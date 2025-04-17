
package Emergencias;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Emergency extends JFrame {
    private JButton findHospitalButton;
    private JButton findContactButton;

    public Emergency() {
        setTitle("Sistema de Emergencias / Paramédicos no encontrados");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Hospital
        findHospitalButton = new JButton("Buscar Hospital Cercano");
        findHospitalButton.setPreferredSize(new Dimension(200, 80));

        // Contacto
        findContactButton = new JButton("Buscar Contacto de Emergencia");
        findContactButton.setPreferredSize(new Dimension(200, 80)); 

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

        panel.add(findHospitalButton);
        panel.add(findContactButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Emergency().setVisible(true);
        });
    }
}
