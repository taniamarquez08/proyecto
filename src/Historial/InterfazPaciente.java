package Historial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InterfazPaciente extends JFrame {
    private JTextField textFieldNombre, textFieldEdad, textFieldPeso, textFieldTipoSangre, textFieldEstatura;
    private JCheckBox checkBoxDiabetes, checkBoxHipertension, checkBoxCorazon;
    private JButton botonGuardar, botonEliminar, botonModificar;
    private JList<String> listaPacientes;
    private DefaultListModel<String> modeloLista;
    private ArrayList<Paciente> registros;

    public InterfazPaciente() {
        super("Historial Paciente");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 10));

        JLabel titulo = new JLabel("Historial Paciente", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setForeground(new Color(0, 0, 128)); // Navy blue
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        inicializarComponentes(panelCentral);
        add(panelCentral, BorderLayout.CENTER);


        configurarEventos();
        this.setVisible(true);

    }

    private void inicializarComponentes(JPanel panel) {
        panel.add(crearFila("Nombre:", textFieldNombre = new JTextField(20)));
        panel.add(crearFila("Edad:", textFieldEdad = new JTextField(20)));
        panel.add(crearFila("Peso:", textFieldPeso = new JTextField(20)));
        panel.add(crearFila("Tipo de Sangre:", textFieldTipoSangre = new JTextField(20)));
        panel.add(crearFila("Estatura:", textFieldEstatura = new JTextField(20)));

        JPanel panelPadecimientos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPadecimientos.add(new JLabel("Padecimientos:"));
        checkBoxDiabetes = new JCheckBox("Diabetes");
        panelPadecimientos.add(checkBoxDiabetes);
        checkBoxHipertension = new JCheckBox("Hipertensi�n");
        panelPadecimientos.add(checkBoxHipertension);
        checkBoxCorazon = new JCheckBox("Problemas de coraz�n");
        panelPadecimientos.add(checkBoxCorazon);
        panel.add(panelPadecimientos);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new JButton("Guardar");
        panelBotones.add(botonGuardar);
        botonEliminar = new JButton("Eliminar");
        panelBotones.add(botonEliminar);
        botonModificar = new JButton("Modificar");
        panelBotones.add(botonModificar);
        panel.add(panelBotones);

        modeloLista = new DefaultListModel<>();
        listaPacientes = new JList<>(modeloLista);
        listaPacientes.setPreferredSize(new Dimension(450, 150));
        JScrollPane scrollPane = new JScrollPane(listaPacientes);
        add(scrollPane, BorderLayout.SOUTH);

        registros = new ArrayList<>();
    }

    private JPanel crearFila(String label, JTextField textField) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(new Color(0, 0, 128)); // Navy blue
        row.add(jLabel);
        row.add(textField);
        return row;
    }

    private void configurarEventos() {
        botonGuardar.addActionListener(e -> {
            guardarPaciente();
            limpiarFormulario();
        });
        botonEliminar.addActionListener(e -> eliminarPaciente());
        botonModificar.addActionListener(e -> modificarPaciente());
    }

    private void guardarPaciente() {
        Paciente p = new Paciente(
            textFieldNombre.getText(),
            textFieldEdad.getText(),
            textFieldPeso.getText(),
            textFieldTipoSangre.getText(),
            textFieldEstatura.getText(),
            checkBoxDiabetes.isSelected(),
            checkBoxHipertension.isSelected(),
            checkBoxCorazon.isSelected()
        );
        registros.add(p);
        modeloLista.addElement(p.toString());
    }

    private void eliminarPaciente() {
        int index = listaPacientes.getSelectedIndex();
        if (index != -1) {
            registros.remove(index);
            modeloLista.remove(index);
        }
    }

    private void modificarPaciente() {
        int index = listaPacientes.getSelectedIndex();
        if (index != -1) {
            Paciente p = registros.get(index);
            p.setNombre(textFieldNombre.getText());
            p.setEdad(textFieldEdad.getText());
            p.setPeso(textFieldPeso.getText());
            p.setTipoSangre(textFieldTipoSangre.getText());
            p.setEstatura(textFieldEstatura.getText());
            p.setDiabetes(checkBoxDiabetes.isSelected());
            p.setHipertension(checkBoxHipertension.isSelected());
            p.setCorazon(checkBoxCorazon.isSelected());
            modeloLista.set(index, p.toString());
        }
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldEdad.setText("");
        textFieldPeso.setText("");
        textFieldTipoSangre.setText("");
        textFieldEstatura.setText("");
        checkBoxDiabetes.setSelected(false);
        checkBoxHipertension.setSelected(false);
        checkBoxCorazon.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazPaciente().setVisible(true));
    }
}

