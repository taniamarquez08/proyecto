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
    private JButton botonEmergencias, botonCitas, botonHistorial, botonExpediente, botonTratamiento, botonJustificantes, botonCancelarCita;
    private Usuario Usuario;
    private JLabel UserLabel;
    private UserBase BaseUsuario;
    private AppointmentBase BaseGlobalCitas;

    public void UpdateUserDisplayFromUser(Usuario U) {
        this.Usuario = U;
        UpdateUserLabel();
        UpdateBotonCitas();
        
    }

    public void UpdateBotonCitas(){
        
        botonCitas.removeActionListener(botonCitas.getActionListeners()[0]);

        if (Usuario.getDoctor()){

            botonCitas.addActionListener(e -> new DoctorInterface(Usuario));

        } else {

            botonCitas.addActionListener(e -> new PatientInterface(this.Usuario, new AppointmentInterface(Usuario)));

        }

    }

    public SignInterface NewSignInterface() {
        return new SignInterface(BaseUsuario, BaseGlobalCitas, this);
    }

    public void UpdateUserLabel(){

        UserLabel.setText("Usuario: " + this.Usuario.getNombre() + " Correo: " + this.Usuario.getEmail());

    }

    public MainMenu(Usuario U) {
        
        BaseUsuario = new UserBase();
        BaseGlobalCitas = new AppointmentBase();

        this.Usuario = U;  
        
        UserLabel = new JLabel(" ");
        UpdateUserLabel(); 

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
        botonHistorial.addActionListener(e -> new InterfazPaciente());
        botonExpediente.addActionListener(e -> new AppGUI(null));
        botonTratamiento.addActionListener(e -> new SeguimientoTratamiento());
        botonJustificantes.addActionListener(e -> new HistorialJustificante().setVisible(true));
        // Nuevo: Al pulsar "Cancelar Cita" se abre AppointmentInterface
        botonCancelarCita.addActionListener(e -> new AppointmentInterface(usuario));

        // Agregar botones al panel con espacio entre ellos
        for (JButton b : new JButton[]{botonEmergencias, botonCitas, botonHistorial, botonExpediente, botonTratamiento, botonJustificantes, botonCancelarCita}) {
            panel.add(b);
        }

        panel.add(UserLabel);

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
