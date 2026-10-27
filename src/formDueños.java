import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.table.*;

// [USER_IMPORTS_START]
//  ESCRIBE TUS IMPORTS AQUI (Ej: import java.io.*;)
    // ----------------------------------------------------
// [USER_IMPORTS_END]

public class formDueños extends JFrame {

    private GestionDueños gestion;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnModificar;
    private JTable tablaDueños;
    private DefaultTableModel modeloTablaDueños;

// [USER_DECLS_START]
//  DECLARA TUS VARIABLES AQUI (Ej: private int contador;)
    // ----------------------------------------------------
// [USER_DECLS_END]

    public formDueños(GestionDueños gestion) {
        this.gestion = gestion;
        setTitle("formDueños");
        setSize(517, 481);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        initComponents();
        initEvents();
    }

    private void initComponents() {
        label1 = new JLabel("Nombre:");
        label1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label1.setBounds(41, 27, 80, 20);
        label2 = new JLabel("Cedula:");
        label2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label2.setBounds(40, 85, 80, 20);
        label3 = new JLabel("Teléfono:");
        label3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label3.setBounds(40, 143, 80, 20);
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtNombre.setBounds(39, 51, 120, 22);
        txtCedula = new JTextField();
        txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtCedula.setBounds(39, 106, 120, 22);
        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtTelefono.setBounds(38, 162, 120, 22);
        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnGuardar.setBounds(65, 198, 90, 25);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEliminar.setBounds(251, 199, 90, 25);
        btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnModificar.setBounds(360, 200, 90, 25);
        modeloTablaDueños = new DefaultTableModel(
            new String[]{"Nombre", "Cedula", "Teléfono"}, 0
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tablaDueños = new JTable(modeloTablaDueños);
        tablaDueños.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaDueños.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll_tablaDueños = new JScrollPane(tablaDueños);
        scroll_tablaDueños.setBounds(40, 238, 419, 170);
        getContentPane().add(label1);
        getContentPane().add(label2);
        getContentPane().add(label3);
        getContentPane().add(txtNombre);
        getContentPane().add(txtCedula);
        getContentPane().add(txtTelefono);
        getContentPane().add(btnGuardar);
        getContentPane().add(btnEliminar);
        getContentPane().add(btnModificar);
        getContentPane().add(scroll_tablaDueños);
    }

    private void initEvents() {
        // [USER_ACTION_START:btnGuardar]
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            int cedula = Integer.parseInt(txtCedula.getText());
            String telefono = txtTelefono.getText();

            Dueño dueño = new Dueño(nombre, cedula, telefono);
            gestion.guardarDueño(dueño);

            actualizarTabla();
            limpiarFormulario();
        });
        // [USER_ACTION_END:btnGuardar]

        // [USER_ACTION_START:btnEliminar]
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaDueños.getSelectedRow();
            if (filaSeleccionada != -1) {
                gestion.eliminarDueño(filaSeleccionada);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un dueño para eliminar.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        // [USER_ACTION_END:btnEliminar]

        // [USER_ACTION_START:btnModificar]
        btnModificar.addActionListener(e -> {
            int filaSeleccionada = tablaDueños.getSelectedRow();
            if (filaSeleccionada != -1) {
                String nombre = txtNombre.getText();
                int cedula = Integer.parseInt(txtCedula.getText());
                String telefono = txtTelefono.getText();

                Dueño dueñoModificado = new Dueño(nombre, cedula, telefono);
                gestion.modificarDueño(filaSeleccionada, dueñoModificado);

                actualizarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un dueño para modificar.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        tablaDueños.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaDueños.rowAtPoint(e.getPoint());
                if (fila != -1) {
                    txtNombre.setText(String.valueOf(modeloTablaDueños.getValueAt(fila, 0)));
                    txtCedula.setText(String.valueOf(modeloTablaDueños.getValueAt(fila, 1)));
                    txtTelefono.setText(String.valueOf(modeloTablaDueños.getValueAt(fila, 2)));
                }
            }
        });
        // [USER_ACTION_END:btnModificar]

    }

// [USER_METHODS_START]
//  AGREGA TUS METODOS PERSONALIZADOS AQUI
    // ----------------------------------------------------
    public void actualizarTabla() {
        modeloTablaDueños.setRowCount(0); // Limpiar la tabla
        for (Dueño dueño : gestion.getDueños()) {
            modeloTablaDueños.addRow(new Object[] { dueño.getNombre(), dueño.getCedula(), dueño.getTelefono() });
        }
    }

    public void limpiarFormulario() {
        this.txtNombre.setText("");
        this.txtCedula.setText("");
        this.txtTelefono.setText("");
    }
// [USER_METHODS_END]
}