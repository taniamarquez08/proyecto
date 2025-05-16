package Expediente;

import MenuPrincipal.MenuInicio;      // para volver al inicio si cierras
import User.Usuario;
import Utilidades.ConexionSQLite;    // tu clase de conexión
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpedientePaciente extends JFrame {
    private final Usuario usuario;
    private final JRadioButton rbtnDatosPrevios;
    private final JRadioButton rbtnConsultas;
    private final JButton btnBuscar;

    public ExpedientePaciente(Usuario usuario) {
        super("Revisar historial médico");
        this.usuario = usuario;

        // --- Ventana principal ---
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        // --- Radio buttons ---
        rbtnDatosPrevios = new JRadioButton("Datos previos ingresados");
        rbtnConsultas    = new JRadioButton("Consultas previas");
        ButtonGroup group = new ButtonGroup();
        group.add(rbtnDatosPrevios);
        group.add(rbtnConsultas);

        JPanel radioPanel = new JPanel(new GridLayout(2,1));
        radioPanel.setBorder(BorderFactory.createTitledBorder("¿Qué desea revisar?"));
        radioPanel.add(rbtnDatosPrevios);
        radioPanel.add(rbtnConsultas);

        // --- Botón Buscar ---
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> onBuscar());

        JPanel south = new JPanel();
        south.add(btnBuscar);

        add(radioPanel, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        // Si cierras esta ventana, regresas al menú inicio
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                new MenuInicio(usuario);
            }
        });

        setVisible(true);
    }

    private void onBuscar() {
        // 1) Validar que haya seleccionado algo
        if (!rbtnDatosPrevios.isSelected() && !rbtnConsultas.isSelected()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, seleccione una opción.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2) Caso “Datos previos”
        if (rbtnDatosPrevios.isSelected()) {
            JOptionPane.showMessageDialog(this,
                "Datos previos ingresados",
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 3) Caso “Consultas previas”: leemos las fechas de la tabla 'base'
        List<String> fechas = fetchConsultationDates(usuario);

        // 4) Si no hay ninguna
        if (fechas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Sin consultas previas.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 5) Si hay fechas, abrimos la ventana de detalle
        new ConsultaDetailInterface(usuario, fechas);
        dispose();
    }

    /**
     * Aquí lee de tu tabla `base`, columna `fecha_consulta`,
     * filtrando por correo_paciente.
     */
    private List<String> fetchConsultationDates(Usuario u) {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT fecha_consulta " +
                     "FROM base " +
                     "WHERE correo_paciente = ? " +
                     "  AND fecha_consulta <> ''";  // opcional: ignora filas sin fecha
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("fecha_consulta"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // Para probar independientemente
    public static void main(String[] args) {
        Usuario dummy = new Usuario(
            "","","paciente@correo.com","","",
            "","","","","", null
        );
        new ExpedientePaciente(dummy);
    }
}
