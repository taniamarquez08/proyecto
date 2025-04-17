/* package RegistroCitas;
import User.Usuario;
import java.awt.*;
import java.util.List;
import javax.swing.*;

class PatientInterface {
    private JFrame frame;
    private JPanel panel;
    private Usuario paciente;
    private JScrollPane scrollPane;
    private JButton refreshButton;

    public PatientInterface(Usuario paciente) {
        this.paciente = paciente;
        frame = new JFrame("Portal del Paciente");
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

    private void AñadirCitaPaciente(Appointment cita){

        paciente.AñadirCita(cita);
        actualizarCitas();

    }

    private void actualizarCitas() {
        panel.removeAll();

        List<Appointment> citas = paciente.getPublicAppointments();
        for (Appointment cita : citas) {
            JButton citaButton = new JButton(cita.getString());
            citaButton.addActionListener(e -> AñadirCitaPaciente(cita));
            panel.add(citaButton);
        }

        panel.revalidate();
        panel.repaint();
    }
} */

package RegistroCitas;
import User.Usuario;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class PatientInterface {
    private JFrame frame;
    private JPanel panel;
    private Usuario paciente;
    private JScrollPane scrollPane;
    private JButton refreshButton;

    private AppointmentInterface Subject;

    public PatientInterface(Usuario paciente, AppointmentInterface S) {
        
        Subject = S;
        
        this.paciente = paciente;
        frame = new JFrame("Portal del Paciente");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> actualizarCitas());
        frame.add(refreshButton, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarCitas();

        frame.setVisible(true);
    }

    private void AñadirCitaPaciente(Appointment cita) {
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que quiere añadir esta cita?", "Confirmar Cita", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            paciente.AñadirCita(cita);
            actualizarCitas();
            Subject.actualizarCitas();
        }
    }

    private void actualizarCitas() {
        panel.removeAll();

        List<Appointment> citas = paciente.getPublicAppointments();
        for (Appointment cita : citas) {
            JPanel citaPanel = new JPanel();
            citaPanel.setLayout(new BorderLayout());
            citaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JLabel citaLabel = new JLabel(cita.getString());
            JButton addButton = new JButton("Añadir Cita");
            addButton.addActionListener(e -> AñadirCitaPaciente(cita));
            
            citaPanel.add(citaLabel, BorderLayout.CENTER);
            citaPanel.add(addButton, BorderLayout.EAST);
            
            panel.add(citaPanel);
        }

        panel.revalidate();
        panel.repaint();
    }
}