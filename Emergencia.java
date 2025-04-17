package proy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Emergencia extends JFrame {
   

    public Emergencia() {
        setTitle("Sistema de Emergencias");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton emergenciaButton = new JButton("EMERGENCIA");
        emergenciaButton.setBackground(Color.WHITE);
        emergenciaButton.setForeground(Color.RED);
        emergenciaButton.setPreferredSize(new Dimension(200,100));
        emergenciaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormularioEmergencia();
            }
        });

        JButton buscarHospitalButton = new JButton("HOSPITALES");
        buscarHospitalButton.setPreferredSize(new Dimension(200,100));
        buscarHospitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Buscando hospitales cercanos...");
            }
                
        });
        
        

        add(emergenciaButton);
        add(buscarHospitalButton);
    }

    private void abrirFormularioEmergencia() {
        JFrame frame = new JFrame("Datos de Emergencia");
        frame.setSize(250, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(10);
        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField(10);

        JLabel codigoLabel = new JLabel("Código: ----");

        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int codigo = 1000 + rand.nextInt(9000);
                codigoLabel.setText("Código: " + codigo);
            }
        });

        JButton localizacionButton = new JButton("Enviar Localización");
        localizacionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ubicación enviada.");
            }
        });

        frame.add(nombreLabel);
        frame.add(nombreField);
        frame.add(apellidoLabel);
        frame.add(apellidoField);
        frame.add(enviarButton);
        frame.add(localizacionButton);
        frame.add(codigoLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Emergencia().setVisible(true);
    }
}

