package MenuPrincipal;

import Emergencias.Emergency;
import Expediente.AppGUI;
import Historial.InterfazPaciente;
import RegistroCitas.AppointmentBase;
import RegistroCitas.AppointmentInterface;
import RegistroCitas.DoctorInterface;
import RegistroCitas.PatientInterface;
import Tratamiento.SeguimientoTratamiento;
import Emergencias.HistorialJustificante;
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

        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Al pulsar la “X” sobre MainMenu, vuelve al MenuInicio con el mismo Usuario
                new MenuInicio(MainMenu.this.Usuario);
            }
        });
        
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton botonEmergencias    = createButton("Emergencia");
        JButton botonCitas         = createButton("Programar Citas");
        JButton botonHistorial     = createButton("Historial Médico");
        JButton botonExpediente    = createButton("Expediente Médico");
        JButton botonTratamiento   = createButton("Seguimiento de Tratamiento");
        JButton botonJustificantes = createButton("Justificante Médico");
        JButton botonCancelarCita  = createButton("Cancelar Cita");

        // ActionListeners desactivados temporalmente
        botonEmergencias.addActionListener(e -> new Emergency().setVisible(true));
        // botonCitas.addActionListener(e -> new PatientInterface(this.Usuario, new AppointmentInterface(Usuario)));
        // botonHistorial.addActionListener(e -> new InterfazPaciente());
        // botonExpediente.addActionListener(e -> new AppGUI(null));
        // botonTratamiento.addActionListener(e -> new SeguimientoTratamiento());
        // botonJustificantes.addActionListener(e -> new HistorialJustificante().setVisible(true));
        // botonCancelarCita.addActionListener(e -> new AppointmentInterface(Usuario));

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
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(botonCancelarCita);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(buttonPanel);

        frame.add(centerWrapper, BorderLayout.CENTER);

        // tras frame.setDefaultCloseOperation(...)
final JFrame f = frame;
frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        f.dispose();  // CIERRA MainMenu
        new MenuInicio(MainMenu.this.Usuario);  // ABRE MenuInicio
    }
});

        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(300, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}

