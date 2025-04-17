package RegistroCitas;

import User.Usuario;
import java.awt.*;
import javax.swing.*;

public class DoctorInterface {
    private JFrame frame;
    private JTextField motivoField, horaInicioField, minutoInicioField;
    private JComboBox<String> diaInicioBox, mesInicioBox;
    private JButton crearCitaButton;
    private Usuario doctor;

    private int año = 2025;

    private static final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private static final int[] DIAS_MES = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public DoctorInterface(Usuario doctor) {
        this.doctor = doctor;
        frame = new JFrame("Gestión de Citas - Doctor");
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2));

        frame.add(new JLabel("Motivo de la cita:"));
        motivoField = new JTextField();
        frame.add(motivoField);

        frame.add(new JLabel("Fecha de Inicio:"));
        JPanel fechaPanel = new JPanel();
        fechaPanel.setLayout(new FlowLayout());

        diaInicioBox = new JComboBox<>();
        mesInicioBox = new JComboBox<>(MESES);
        actualizarDias();

        mesInicioBox.addActionListener(e -> actualizarDias());
        fechaPanel.add(diaInicioBox);
        fechaPanel.add(mesInicioBox);
        fechaPanel.add(new JLabel(Integer.toString(año)));

        frame.add(fechaPanel);

        frame.add(new JLabel("Hora de inicio (HH:MM):"));
        JPanel horaPanel = new JPanel();
        horaPanel.setLayout(new FlowLayout());
        horaInicioField = new JTextField(2);
        minutoInicioField = new JTextField(2);
        horaPanel.add(horaInicioField);
        horaPanel.add(new JLabel(":"));
        horaPanel.add(minutoInicioField);
        frame.add(horaPanel);

        crearCitaButton = new JButton("Crear Cita");
        frame.add(crearCitaButton);

        crearCitaButton.addActionListener(e -> crearCita());

        frame.setVisible(true);
    }

    private void actualizarDias() {
        int mesIndex = mesInicioBox.getSelectedIndex();
        int dias = DIAS_MES[mesIndex];

        if (año % 4 == 0){

            DIAS_MES[1] = 29;

        } else{

            DIAS_MES[1] = 28;

        }

        diaInicioBox.removeAllItems();

        for (int i = 1; i <= dias; i++) {
            diaInicioBox.addItem(String.valueOf(i));
        }
    }

    private void crearCita() {
        try {
            String motivo = motivoField.getText().trim();
            String horaStr = horaInicioField.getText().trim();
            String minutoStr = minutoInicioField.getText().trim();
            
            if (motivo.isEmpty() || horaStr.isEmpty() || minutoStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int hora = Integer.parseInt(horaStr);
            int minuto = Integer.parseInt(minutoStr);
            if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59) {
                JOptionPane.showMessageDialog(frame, "Ingrese una hora válida (00-23) y minutos válidos (00-59).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int dia = Integer.parseInt((String) diaInicioBox.getSelectedItem());
            int mes = mesInicioBox.getSelectedIndex() + 1;
            

            DateTime inicio = new DateTime(año, mes, dia, hora, minuto);
            doctor.CrearCita(motivo, inicio);
            JOptionPane.showMessageDialog(frame, "Cita creada con éxito");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}