package Expediente;
import Utilidades.ConexionSQLite;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpedienteNuevo extends JFrame {
    private static int contadorExpedientes = 100000; // Empieza desde 100000

    private JComboBox<String> cmbPacientes;
    private JTextArea txtDiagnostico;
    private JComboBox<String> cmbMedicamentos;
    private JComboBox<Integer> cmbDias;
    private JComboBox<String> cmbFrecuencia;
    private JCheckBox chk1, chk2, chk3, chk4, chkOtro;
    private JTextField txtOtroSintoma;

    public ExpedienteNuevo() {
        super("Sistema de Gestión de Expedientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 800);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // ── PANEL SUPERIOR (fecha + expediente + paciente) ───────────────────
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFecha = new JLabel("Fecha: " +
            new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        JLabel lblExp = new JLabel("#Expediente: " + (contadorExpedientes));
        panelSuperior.add(lblFecha);
        panelSuperior.add(Box.createHorizontalStrut(50));
        panelSuperior.add(lblExp);

        // 1) Cargamos la lista de pacientes desde la BD
        DefaultComboBoxModel<String> mdl = new DefaultComboBoxModel<>();
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(
               "SELECT nombre_paciente || ' ' || apellido_paciente FROM base"
             );
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) {
                mdl.addElement(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
              "Error cargando pacientes: " + ex.getMessage(),
              "Error BD", JOptionPane.ERROR_MESSAGE);
        }
        cmbPacientes = new JComboBox<>(mdl);
        panelSuperior.add(new JLabel("Paciente:"));
        panelSuperior.add(cmbPacientes);

        add(panelSuperior);

        // ── Resto de la interfaz ───────────────────────────────────────────────
        // Logo
        JPanel panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(100, 50));
        panelLogo.setBorder(BorderFactory.createTitledBorder("Logo"));
        add(panelLogo);

        // Signos vitales (no conectados a BD)
        JPanel panelSignos = new JPanel(new GridLayout(4,2));
        panelSignos.setBorder(BorderFactory.createTitledBorder("Signos vitales"));
        panelSignos.add(new JLabel("Frecuencia cardiaca:"));
        panelSignos.add(new JTextField());
        panelSignos.add(new JLabel("Frecuencia respiratoria:"));
        panelSignos.add(new JTextField());
        panelSignos.add(new JLabel("Presión arterial:"));
        panelSignos.add(new JTextField());
        panelSignos.add(new JLabel("Temperatura:"));
        panelSignos.add(new JTextField());
        add(panelSignos);

        // Síntomas
        JPanel panelSintomas = new JPanel();
        panelSintomas.setLayout(new BoxLayout(panelSintomas, BoxLayout.Y_AXIS));
        panelSintomas.setBorder(BorderFactory.createTitledBorder("Síntomas"));
        chk1 = new JCheckBox("Dificultad para respirar");
        chk2 = new JCheckBox("Fiebre");
        chk3 = new JCheckBox("Dolor de cabeza");
        chk4 = new JCheckBox("Dolor abdominal");
        chkOtro = new JCheckBox("Otro (especifique):");
        txtOtroSintoma = new JTextField();
        txtOtroSintoma.setEnabled(false);
        chkOtro.addActionListener(e -> txtOtroSintoma.setEnabled(chkOtro.isSelected()));
        panelSintomas.add(chk1);
        panelSintomas.add(chk2);
        panelSintomas.add(chk3);
        panelSintomas.add(chk4);
        panelSintomas.add(chkOtro);
        panelSintomas.add(txtOtroSintoma);
        add(panelSintomas);

        // Diagnóstico
        JPanel panelDiag = new JPanel();
        panelDiag.setBorder(BorderFactory.createTitledBorder("Diagnóstico"));
        txtDiagnostico = new JTextArea(4, 40);
        panelDiag.add(new JScrollPane(txtDiagnostico));
        add(panelDiag);

        // Estudios
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
        eOtro.addActionListener(evt -> txtOtroEstudio.setEnabled(eOtro.isSelected()));
        panelEstudios.add(e1);
        panelEstudios.add(e2);
        panelEstudios.add(e3);
        panelEstudios.add(e4);
        panelEstudios.add(eOtro);
        panelEstudios.add(txtOtroEstudio);
        add(panelEstudios);

        // Receta
        JPanel panelReceta = new JPanel(new FlowLayout());
        panelReceta.setBorder(BorderFactory.createTitledBorder("Receta"));
        String[] meds = {
          "Aspirina","Omeprazol","Paracetamol",
          "Ibuprofeno","Redoxon","Simi-Fibra",
          "Tukol-D","Pastillas de miel",
          "Claritin","Pepto Bismol"
        };
        cmbMedicamentos = new JComboBox<>(meds);
        cmbDias         = new JComboBox<>();
        for (int i=1; i<=31; i++) cmbDias.addItem(i);
        cmbFrecuencia   = new JComboBox<>(new String[]{"8 hrs","12 hrs","24 hrs"});
        panelReceta.add(new JLabel("Medicamento:"));
        panelReceta.add(cmbMedicamentos);
        panelReceta.add(new JLabel("# Días:"));
        panelReceta.add(cmbDias);
        panelReceta.add(new JLabel("C/"));
        panelReceta.add(cmbFrecuencia);
        panelReceta.add(new JLabel("Firma médico (ID):"));
        JTextField txtFirma = new JTextField(6);
        panelReceta.add(txtFirma);
        add(panelReceta);

        // Botón “Actualizar Expediente”
        JButton btnActualizar = new JButton("Actualizar Expediente");
        btnActualizar.addActionListener(evt -> {
            String paciente  = (String)cmbPacientes.getSelectedItem();
            String sintomas  = buildSintomas();
            String diagnost  = txtDiagnostico.getText().trim();
            String med       = (String)cmbMedicamentos.getSelectedItem();
            int    dias      = (Integer)cmbDias.getSelectedItem();
            String freq      = (String)cmbFrecuencia.getSelectedItem();
            String firmaDoc  = txtFirma.getText().trim();

            String sql = "INSERT INTO base ("
                       + "sintomas, diagnostico, receta_medicamento,"
                       + "receta_dias, receta_frecuencia, firma_doctor,"
                       + "nombre_paciente, apellido_paciente, correo_paciente"
                       + ") VALUES (?,?,?,?,?,?,?,?,?)";
            try (Connection conn = ConexionSQLite.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1, sintomas);
                ps.setString(2, diagnost);
                ps.setString(3, med);
                ps.setInt   (4, dias);
                ps.setString(5, freq);
                ps.setString(6, firmaDoc);
                // desglosamos el nombre completo para guardarlo también
                String[] parts = paciente.split(" ",2);
                ps.setString(7, parts[0]);
                ps.setString(8, parts.length>1 ? parts[1] : "");
                ps.setString(9, ""); // podrías buscar el correo real si lo necesitas
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this,
                  "Expediente guardado correctamente.",
                  "Éxito", JOptionPane.INFORMATION_MESSAGE);
                contadorExpedientes++;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                  "Error al guardar expediente: "+ex.getMessage(),
                  "Error BD", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnActualizar);

        pack();
        setVisible(true);
    }

    /** Auxiliar para concatenar checkboxes de síntomas */
    private String buildSintomas() {
        StringBuilder sb = new StringBuilder();
        if (chk1.isSelected()) sb.append("Dificultad para respirar, ");
        if (chk2.isSelected()) sb.append("Fiebre, ");
        if (chk3.isSelected()) sb.append("Dolor de cabeza, ");
        if (chk4.isSelected()) sb.append("Dolor abdominal, ");
        if (chkOtro.isSelected()) sb.append(txtOtroSintoma.getText().trim()).append(", ");
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExpedienteNuevo::new);
    }
}