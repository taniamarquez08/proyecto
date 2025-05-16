package RegistroCitas;

import Expediente.AppGUI;
import Expediente.SistemaExpediente;
import User.Usuario;
import Emergencias.Emergency;
import javax.swing.*;
import java.awt.*;

public class DoctorMenuInterface {
    private JFrame frame;
    private JPanel buttonPanel;
    private JButton btnProgramarCita, btnVerExpediente, btnRegistrarConsulta, btnHistorialPrevio, btnVerSeguimiento;

    public DoctorMenuInterface() {
        frame = new JFrame("Menú para el Doctor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear botones
        btnProgramarCita    = createButton("Programar Cita");
        btnVerExpediente    = createButton("Ver Expediente Médico");
        btnRegistrarConsulta= createButton("Registrar Consulta");
        btnHistorialPrevio  = createButton("Historial Previo");
        btnVerSeguimiento   = createButton("Ver Seguimiento de Tratamiento");

        // Agregar ActionListeners después de crear cada botón
        btnProgramarCita.addActionListener(e -> new DoctorInterface(new Usuario("", "", "", "", "", "", "", "", "", "", new AppointmentBase())));
        btnRegistrarConsulta.addActionListener(e -> new AppGUI(new SistemaExpediente()));
        // Los demás botones los puedes activar igual cuando tengas sus interfaces
        // btnVerExpediente.addActionListener(e -> new VerExpedienteInterface().setVisible(true));
        // btnHistorialPrevio.addActionListener(e -> new HistorialPrevioInterface().setVisible(true));
        // btnVerSeguimiento.addActionListener(e -> new SeguimientoTratamientoInterface().setVisible(true));

        // Apilar botones con espacio
        buttonPanel.add(btnProgramarCita);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnVerExpediente);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnRegistrarConsulta);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnHistorialPrevio);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnVerSeguimiento);

        frame.add(new JPanel(new GridBagLayout()) {{ add(buttonPanel); }}, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(300, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DoctorMenuInterface::new);
    }
}
