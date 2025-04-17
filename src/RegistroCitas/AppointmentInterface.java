package RegistroCitas;
import User.Usuario;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class AppointmentInterface {
    private JFrame frame;
    private JPanel panel;
    private Usuario paciente;
    private JScrollPane scrollPane;
    private JButton refreshButton;

    public AppointmentInterface(Usuario paciente) {
        this.paciente = paciente;
        frame = new JFrame("Mis Citas");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> actualizarCitas());
        frame.add(refreshButton, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarCitas();

        frame.setVisible(true);
    }

    private void QuitarCitaPaciente(Appointment cita) {
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que quiere quitar esta cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            paciente.CancelarCita(cita);
            actualizarCitas();
        }
    }

    public void actualizarCitas() {
        panel.removeAll();

        List<Appointment> citas = paciente.GetMyAppointments();
        for (Appointment cita : citas) {
            JPanel citaPanel = new JPanel();
            citaPanel.setLayout(new BorderLayout());
            citaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JLabel citaLabel = new JLabel(cita.getString());
            JButton quitarButton = new JButton("Quitar Cita");
            quitarButton.addActionListener(e -> QuitarCitaPaciente(cita));
            
            citaPanel.add(citaLabel, BorderLayout.CENTER);
            citaPanel.add(quitarButton, BorderLayout.EAST);
            
            panel.add(citaPanel);
        }

        panel.revalidate();
        panel.repaint();
    }
}
