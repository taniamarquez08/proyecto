package proy;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrador extends JFrame {
    private JButton verificarDoctoresButton;
    private JButton eliminarCuentasButton;
    private JButton administrarCitasButton;
    private JButton menuPagosButton;

    public Administrador() {
        setTitle("Administrador");
        setSize(400, 350);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); 
   
        verificarDoctoresButton = new JButton("Verificar Doctor");
        eliminarCuentasButton = new JButton("Eliminar Cuentas");
        administrarCitasButton = new JButton("Administrar Citas");
        menuPagosButton = new JButton("Menú de Pagos");

     
        Dimension buttonSize = new Dimension(250, 50);  
        verificarDoctoresButton.setPreferredSize(buttonSize);
        eliminarCuentasButton.setPreferredSize(buttonSize);
        administrarCitasButton.setPreferredSize(buttonSize);
        menuPagosButton.setPreferredSize(buttonSize);

        verificarDoctoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Verificando Doctor...");
            }
        });

        eliminarCuentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Eliminando Cuenta...");
            }
        });

        administrarCitasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Administrar Cita...");
            }
        });

        menuPagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abriendo Menú de pagos...");
            }
        });

        panel.add(verificarDoctoresButton);
        panel.add(eliminarCuentasButton);
        panel.add(administrarCitasButton);
        panel.add(menuPagosButton);

        add(panel);

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Administrador().setVisible(true);
        });
    }
}




      

