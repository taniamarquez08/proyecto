package Expediente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;                    // Import específico para Date
import java.sql.*;                       // Para la conexión JDBC
import Utilidades.ConexionSQLite;       // Tu clase de conexión

public class ExpedienteNuevo extends JFrame {
    private static int contadorExpedientes = 100000; // Empieza desde 100000
    private JTextArea txtAreaExpedientes = new JTextArea();
    private JComboBox<String> comboPacientes;

    public ExpedienteNuevo() {
        super("Sistema de Gestión de Expedientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // — Panel superior con fecha, número y lista de pacientes —
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFecha = new JLabel(
            "Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date())
        );
        JLabel lblExpediente = new JLabel("#Expediente: " + contadorExpedientes);
        JLabel lblPaciente = new JLabel("Paciente:");
        comboPacientes = new JComboBox<>();

        // Poblar comboPacientes desde la tabla base (solo los pacientes)
        try (Connection conn = ConexionSQLite.conectar()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "No se pudo conectar con la base de datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                PreparedStatement ps = conn.prepareStatement(
                    "SELECT nombre_paciente, apellido_paciente " +
                    "FROM base " +
                    "WHERE correo_paciente IS NOT NULL AND correo_paciente <> ''"
                );
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nombre = rs.getString("nombre_paciente");
                    String apellido = rs.getString("apellido_paciente");
                    comboPacientes.addItem(nombre + " " + apellido);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Error cargando pacientes: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        panelSuperior.add(lblFecha);
        panelSuperior.add(Box.createHorizontalStrut(50));
        panelSuperior.add(lblExpediente);
        panelSuperior.add(Box.createHorizontalStrut(20));
        panelSuperior.add(lblPaciente);
        panelSuperior.add(comboPacientes);
        add(panelSuperior);

        // — Logo vacío —
        JPanel panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(100, 50));
        panelLogo.setBorder(BorderFactory.createTitledBorder("Logo"));
        add(panelLogo);

        // — Signos vitales —
        JPanel panelSignos = new JPanel(new GridLayout(4, 2));
        panelSignos.setBorder(BorderFactory.createTitledBorder("Signos vitales"));
        panelSignos.add(new JLabel("Frecuencia cardiaca:"));
        JTextField txtFc = new JTextField(); panelSignos.add(txtFc);
        panelSignos.add(new JLabel("Frecuencia respiratoria:"));
        JTextField txtFr = new JTextField(); panelSignos.add(txtFr);
        panelSignos.add(new JLabel("Presión arterial:"));
        JTextField txtPa = new JTextField(); panelSignos.add(txtPa);
        panelSignos.add(new JLabel("Temperatura:"));
        JTextField txtTemp = new JTextField(); panelSignos.add(txtTemp);
        add(panelSignos);

        // — Síntomas —
        JPanel panelSintomas = new JPanel();
        panelSintomas.setLayout(new BoxLayout(panelSintomas, BoxLayout.Y_AXIS));
        panelSintomas.setBorder(BorderFactory.createTitledBorder("Síntomas"));
        JCheckBox s1 = new JCheckBox("Dificultad para respirar");
        JCheckBox s2 = new JCheckBox("Fiebre");
        JCheckBox s3 = new JCheckBox("Dolor de cabeza");
        JCheckBox s4 = new JCheckBox("Dolor abdominal");
        JCheckBox sOtro = new JCheckBox("Otro (especifique):");
        JTextField txtOtroSintoma = new JTextField();
        txtOtroSintoma.setEnabled(false);
        sOtro.addActionListener(e -> txtOtroSintoma.setEnabled(sOtro.isSelected()));
        panelSintomas.add(s1);
        panelSintomas.add(s2);
        panelSintomas.add(s3);
        panelSintomas.add(s4);
        panelSintomas.add(sOtro);
        panelSintomas.add(txtOtroSintoma);
        add(panelSintomas);

        // — Diagnóstico —
        JPanel panelDiagnostico = new JPanel();
        panelDiagnostico.setBorder(BorderFactory.createTitledBorder("Diagnóstico"));
        JTextArea txtDiagnostico = new JTextArea(4, 40);
        panelDiagnostico.add(new JScrollPane(txtDiagnostico));
        add(panelDiagnostico);

        // — Estudios —
        JPanel panelEstudios = new JPanel();
        panelEstudios.setLayout(new BoxLayout(panelEstudios, BoxLayout.Y_AXIS));
        panelEstudios.setBorder(BorderFactory.createTitledBorder("Estudios"));
        JCheckBox e1 = new JCheckBox("Análisis de sangre");
        JCheckBox e2 = new JCheckBox("Ultrasonido");
        JCheckBox e3 = new JCheckBox("Resonancia magnética");
        JCheckBox e4 = new JCheckBox("Radiografía");
        JCheckBox eOtro = new JCheckBox("Otro (especifique):");
        JTextField txtOtroEstudio = new JTextField();
        txtOtroEstudio.setEnabled(false);
        eOtro.addActionListener(e -> txtOtroEstudio.setEnabled(eOtro.isSelected()));
        panelEstudios.add(e1);
        panelEstudios.add(e2);
        panelEstudios.add(e3);
        panelEstudios.add(e4);
        panelEstudios.add(eOtro);
        panelEstudios.add(txtOtroEstudio);
        add(panelEstudios);

        // — Receta —
        JPanel panelReceta = new JPanel(new FlowLayout());
        panelReceta.setBorder(BorderFactory.createTitledBorder("Receta"));
        String[] medicamentos = {
            "Aspirina", "Omeprazol", "Paracetamol", "Ibuprofeno",
            "Redoxon", "Simi-Fibra", "Tukol-D", "Pastillas de miel",
            "Claritin", "Pepto Bismol"
        };
        JComboBox<String> cmbMedicamentos = new JComboBox<>(medicamentos);
        JComboBox<Integer> cmbDias = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cmbDias.addItem(i);
        JComboBox<String> cmbFrecuencia = new JComboBox<>(
            new String[]{"8 hrs", "12 hrs", "24 hrs"}
        );
        JTextField txtFirma = new JTextField(10);
        panelReceta.add(new JLabel("Medicamento:"));
        panelReceta.add(cmbMedicamentos);
        panelReceta.add(new JLabel("# Días:"));
        panelReceta.add(cmbDias);
        panelReceta.add(new JLabel("C/"));
        panelReceta.add(cmbFrecuencia);
        panelReceta.add(new JLabel("Firma médico:"));
        panelReceta.add(txtFirma);
        add(panelReceta);

        // — Botón “Actualizar Expediente” —
        JButton btnActualizar = new JButton("Actualizar Expediente");
        btnActualizar.addActionListener(e -> {
            StringBuilder m = new StringBuilder();
            m.append("Expediente Guardado Exitosamente:\n");
            m.append("Número: ").append(contadorExpedientes).append("\n");
            m.append("Paciente: ").append(comboPacientes.getSelectedItem()).append("\n");
            // … (puedes añadir más datos aquí)
            JOptionPane.showMessageDialog(
                this, m.toString(),
                "Expediente Guardado",
                JOptionPane.INFORMATION_MESSAGE
            );
            contadorExpedientes++;
            lblExpediente.setText("#Expediente: " + contadorExpedientes);
        });
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        add(panelBoton);

        // — Área de texto (si decides usarla) —
        txtAreaExpedientes.setEditable(false);
        add(new JScrollPane(txtAreaExpedientes));

        setVisible(true);
    }

}

