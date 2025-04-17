package MenuPrincipal;

import Emergencias.Emergency;
import Expediente.AppGUI;
import Historial.InterfazPaciente;
import RegistroCitas.AppointmentInterface;
import Tratamiento.SeguimientoTratamiento;
import Emergencias.HistorialJustificante;
import RegistroCitas.SignInterface;
import User.Usuario;
import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JButton botonEmergencias, botonCitas, botonHistorial, botonExpediente, botonTratamiento, botonJustificantes, botonCancelarCita;

    public MainMenu(Usuario usuario) {
        JFrame frame = new JFrame("MedConnect");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Crear botones con sus etiquetas
        botonEmergencias    = createButton("Emergencia");
        botonCitas         = createButton("Programar Citas");
        botonHistorial     = createButton("Historial Médico");
        botonExpediente    = createButton("Expediente Médico");
        botonTratamiento   = createButton("Seguimiento de Tratamiento");
        botonJustificantes = createButton("Justificante Médico");
        botonCancelarCita  = createButton("Cancelar Cita");

        // Asignar ActionListeners (funcionalidad original)
        botonEmergencias.addActionListener(e -> new Emergency().setVisible(true));
        botonCitas.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Usuario aun no registrado"));
        botonHistorial.addActionListener(e -> new InterfazPaciente().setVisible(true));
        botonExpediente.addActionListener(e -> new AppGUI(null));
        botonTratamiento.addActionListener(e -> new SeguimientoTratamiento());
        botonJustificantes.addActionListener(e -> new HistorialJustificante().setVisible(true));
        // Nuevo: Al pulsar "Cancelar Cita" se abre AppointmentInterface
        botonCancelarCita.addActionListener(e -> new AppointmentInterface(usuario));

        // Agregar botones al panel con espacio entre ellos
        for (JButton b : new JButton[]{botonEmergencias, botonCitas, botonHistorial, botonExpediente, botonTratamiento, botonJustificantes, botonCancelarCita}) {
            panel.add(b);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(300, 50));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

}
