package MenuPrincipal;

import Emergencias.Emergency;
import Expediente.ExpedienteNuevo;  // <-- Importamos la clase modificada
import Historial.InterfazPaciente;
import RegistroCitas.AppointmentBase;
import RegistroCitas.AppointmentInterface;
import RegistroCitas.DoctorInterface;
import RegistroCitas.PatientInterface;
// import Tratamiento.SeguimientoTratamiento;
// import Emergencias.HistorialJustificante;
import RegistroCitas.SignInterface;
import User.UserBase;
import User.Usuario;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private Usuario Usuario;
    private UserBase BaseUsuario;
    private AppointmentBase BaseGlobalCitas;

    public void UpdateUserDisplayFromUser(Usuario U) {
        this.Usuario = U;
    }

    public SignInterface NewSignInterface() {
        return new SignInterface(BaseUsuario, BaseGlobalCitas, this);
    }

    public MainMenu(Usuario U) {
        this.Usuario = U;
        BaseUsuario = new UserBase();
        BaseGlobalCitas = new AppointmentBase();

        JFrame frame = new JFrame("MedConnect");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Cuando cierres MainMenu con la X, vuelves a MenuInicio(Persistiendo Usuario)
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                new MenuInicio(MainMenu.this.Usuario);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton botonEmergencias    = createButton("Emergencia");
        JButton botonCitas         = createButton("Programar Citas");
        JButton botonHistorial     = createButton("Historial Médico");
        JButton botonExpediente    = createButton("Expediente Médico");
        JButton botonTratamiento   = createButton("Seguimiento de Tratamiento");
        JButton botonJustificantes = createButton("Justificante Médico");
        // JButton botonCancelarCita  = createButton("Cancelar Cita"); (Cancelar cita ya viene adentro de AppointmentInterface)

        // aqui quien sabe quien quito los actionlisteners >:(

        botonEmergencias.addActionListener(e -> new Emergency().setVisible(true));
        botonCitas.addActionListener(e -> new AppointmentInterface(this.Usuario));
        botonHistorial.addActionListener(e -> new InterfazPaciente());
        botonExpediente.addActionListener(null);
        botonTratamiento.addActionListener(null);
        botonJustificantes.addActionListener(null); // Estos 3 supuestamente lo hacian los que se dieron de baja


        buttonPanel.add(botonEmergencias);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(botonCitas);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(botonHistorial);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(botonExpediente);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(botonTratamiento);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(botonJustificantes);
        // buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        // buttonPanel.add(botonCancelarCita);
        // y el GridBagLayout?? :(

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(buttonPanel);

        frame.add(centerWrapper, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(300, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}
