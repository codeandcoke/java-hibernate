package org.sfaci.gestion.gui;

import org.hibernate.HibernateException;
import org.sfaci.gestion.HibernateUtil;

import javax.swing.*;

/**
 * GUI
 */
public class Ventana {
    private JPanel panel1;
    public JTabbedPane tabbedPane1;
    public JTextField tfNombreCliente;
    public JTextField tfApellidos;
    public JTextField tfEmail;
    public JTextField tfTelefono;
    public JList lClientes;
    public JButton btNuevoCliente;
    public JButton btGuardarCliente;
    public JButton btCancelarCliente;
    public JButton btModificarCliente;
    public JButton btEliminarCliente;
    public JLabel lbEstado;
    public JTextField textField1;
    public JComboBox comboBox1;
    public JButton btNuevoPedido;
    public JButton btGuardarPedido;
    public JList list1;
    public JButton btCancelarPedido;
    public JButton btModificarPedido;
    public JButton btEliminarPedido;
    public JButton btEliminarDetalle;
    public JButton btNuevoDetalle;
    public JButton btModificarDetalle;

    public DefaultListModel modeloListaClientes;

    public Ventana() {
        JFrame frame = new JFrame("Gestión");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        inicializar();
    }

    private void inicializar() {
        modeloListaClientes = new DefaultListModel();
        lClientes.setModel(modeloListaClientes);
    }
}
