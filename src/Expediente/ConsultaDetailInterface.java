package Expediente;

import User.Usuario;
import Utilidades.ConexionSQLite;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

import java.util.List;

public class ConsultaDetailInterface extends JFrame {
    private Usuario usuario;
    private JComboBox<String> cmbFechas;
    private JTextField txtFecha, txtDoctor, txtExpediente, txtFirma;
    private JTextField txtFc, txtFr, txtPa, txtTemp;
    private JTextArea txtSintomas, txtDiagnostico, txtEstudios;
    private JTable tblMedicamento;

    public ConsultaDetailInterface(Usuario usuario, List<String> fechas) {
        super("Detalle de Consulta");
        this.usuario = usuario;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel superior: selección de fecha
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Consultar fecha:"));
        cmbFechas = new JComboBox<>(fechas.toArray(new String[0]));
        top.add(cmbFechas);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> cargarDatos((String)cmbFechas.getSelectedItem()));
        top.add(btnBuscar);

        // Panel info general
        JPanel infoGen = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        txtFecha      = createReadonlyField(10);
        txtDoctor     = createReadonlyField(15);
        txtExpediente = createReadonlyField(5);
        txtFirma      = createReadonlyField(8);
        infoGen.add(new JLabel("Fecha:"));      infoGen.add(txtFecha);
        infoGen.add(new JLabel("Doctor:"));     infoGen.add(txtDoctor);
        infoGen.add(new JLabel("#Expediente:"));infoGen.add(txtExpediente);
        infoGen.add(new JLabel("Firma:"));      infoGen.add(txtFirma);

        // Panel signos y síntomas
        JPanel center = new JPanel(new GridLayout(1,2,10,10));
        // signos
        JPanel pSignos = new JPanel(new GridLayout(4,2,5,5));
        pSignos.setBorder(BorderFactory.createTitledBorder("Signos vitales"));
        txtFc  = createReadonlyField(5);
        txtFr  = createReadonlyField(5);
        txtPa  = createReadonlyField(5);
        txtTemp= createReadonlyField(5);
        pSignos.add(new JLabel("FC:")); pSignos.add(txtFc);
        pSignos.add(new JLabel("FR:")); pSignos.add(txtFr);
        pSignos.add(new JLabel("PA:")); pSignos.add(txtPa);
        pSignos.add(new JLabel("Temp:"));pSignos.add(txtTemp);
        // sintomas
        JPanel pSint = new JPanel(new BorderLayout());
        pSint.setBorder(BorderFactory.createTitledBorder("Síntomas"));
        txtSintomas = new JTextArea(5,20);
        txtSintomas.setEditable(false);
        pSint.add(new JScrollPane(txtSintomas), BorderLayout.CENTER);

        center.add(pSignos);
        center.add(pSint);

        // Diagnóstico
        JPanel pDiag = new JPanel(new BorderLayout());
        pDiag.setBorder(BorderFactory.createTitledBorder("Diagnóstico"));
        txtDiagnostico = new JTextArea(3,50);
        txtDiagnostico.setEditable(false);
        pDiag.add(new JScrollPane(txtDiagnostico), BorderLayout.CENTER);

        // Receta (tabla)
        String[] cols = {"Medicamento","Días","Frecuencia"};
        tblMedicamento = new JTable(new DefaultTableModel(cols, 0));
        tblMedicamento.setEnabled(false);
        JPanel pRec = new JPanel(new BorderLayout());
        pRec.setBorder(BorderFactory.createTitledBorder("Receta"));
        pRec.add(new JScrollPane(tblMedicamento), BorderLayout.CENTER);

        // Estudios
        JPanel pEst = new JPanel(new BorderLayout());
        pEst.setBorder(BorderFactory.createTitledBorder("Estudios"));
        txtEstudios = new JTextArea(3,30);
        txtEstudios.setEditable(false);
        pEst.add(new JScrollPane(txtEstudios), BorderLayout.CENTER);

        // Montaje final
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(top);
        content.add(infoGen);
        content.add(center);
        content.add(pDiag);
        content.add(pRec);
        content.add(pEst);

        add(new JScrollPane(content));
        setVisible(true);

        // Carga inicial para la primera fecha
        if (!fechas.isEmpty()) {
            cargarDatos(fechas.get(0));
        }
    }

    private JTextField createReadonlyField(int cols) {
        JTextField f = new JTextField(cols);
        f.setEditable(false);
        return f;
    }

    /** Carga todos los campos desde la BD para la fecha dada */
    private void cargarDatos(String fecha) {
        String sql = "SELECT * FROM consultas "
                   + "WHERE correo_paciente = ? AND fecha_consulta = ?";
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getEmail());
            ps.setString(2, fecha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtFecha.setText(rs.getString("fecha_consulta"));
                txtDoctor.setText(rs.getString("doctor_nombre"));
                txtExpediente.setText(rs.getString("num_expediente"));
                txtFirma.setText(rs.getString("firma_doctor"));

                txtFc.setText(rs.getString("fc"));
                txtFr.setText(rs.getString("fr"));
                txtPa.setText(rs.getString("pa"));
                txtTemp.setText(rs.getString("temp"));

                txtSintomas.setText(rs.getString("sintomas"));
                txtDiagnostico.setText(rs.getString("diagnostico"));
                txtEstudios.setText(rs.getString("estudios"));

                // tabla receta
                DefaultTableModel m = (DefaultTableModel) tblMedicamento.getModel();
                m.setRowCount(0);
                String med = rs.getString("receta_medicamento");
                int dias  = rs.getInt("receta_dias");
                String freq= rs.getString("receta_frecuencia");
                m.addRow(new Object[]{med, dias, freq});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar datos: "+ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

