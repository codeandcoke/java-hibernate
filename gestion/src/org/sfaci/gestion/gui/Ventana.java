package org.sfaci.gestion.gui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * GUI
 */
public class Ventana {
    private JPanel panel1;
    public JTabbedPane tbPanel;
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
    public JTextField tfNumeroPedido;
    public JComboBox cbClientePedido;
    public JButton btNuevoPedido;
    public JButton btGuardarPedido;
    public JList lPedidos;
    public JButton btCancelarPedido;
    public JButton btModificarPedido;
    public JButton btEliminarPedido;
    public JButton btEliminarDetalle;
    public JButton btNuevoDetalle;
    public JButton btModificarDetalle;
    public JTable tDetalles;
    public JDateChooser dcFechaPedido;
    public JDateChooser dcFechaEntregaPedido;
    public JButton btNuevoProducto;
    public JButton btGuardarProducto;
    public JFormattedTextField tfPrecioProducto;
    public JTextField tfDescripcionProducto;
    public JTextField tfNombreProducto;

    public DefaultListModel modeloListaClientes;
    public DefaultListModel modeloListaPedidos;
    public DefaultTableModel modeloTablaDetalles;

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

        modeloTablaDetalles = new DefaultTableModel();
        tDetalles.setModel(modeloTablaDetalles);

        modeloListaPedidos = new DefaultListModel();
        lPedidos.setModel(modeloListaPedidos);

        modeloTablaDetalles.addColumn("nombre");
        modeloTablaDetalles.addColumn("unidades");
        modeloTablaDetalles.addColumn("precio/u");
        modeloTablaDetalles.addColumn("subtotal");
    }
}
