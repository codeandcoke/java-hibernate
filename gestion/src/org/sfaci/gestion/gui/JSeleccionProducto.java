package org.sfaci.gestion.gui;

import org.hibernate.Query;
import org.sfaci.gestion.HibernateUtil;
import org.sfaci.gestion.base.Producto;
import org.sfaci.gestion.util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class JSeleccionProducto extends JDialog
    implements ActionListener, KeyListener, FocusListener {
    private JPanel contentPane;
    private JTextField tfBusquedaProducto;
    private JTextField tfCantidadProducto;
    private JTable tProductos;
    private JButton btOk;
    private JButton btCancelar;

    private DefaultTableModel modeloTabla;

    private Producto producto;
    private int cantidad;

    public JSeleccionProducto() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setModal(true);
        pack();

        inicializar();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void mostrarDialogo() {
        setVisible(true);
    }

    private void inicializar() {

        tfBusquedaProducto.requestFocus();
        tfBusquedaProducto.addKeyListener(this);
        tfBusquedaProducto.addFocusListener(this);

        btCancelar.addActionListener(this);
        btOk.addActionListener(this);

        modeloTabla = new DefaultTableModel();
        tProductos.setModel(modeloTabla);
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Precio");

        listarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btOk) {
            if (tfCantidadProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Indica la cantidad",
                        "Seleccionar Producto", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cantidad = Integer.parseInt(tfCantidadProducto.getText());
            producto = getProductoSeleccionado();
        }
        else {

        }
        setVisible(false);
    }

    private Producto getProductoSeleccionado() {

        String nombreProducto = (String)
                tProductos.getValueAt(tProductos.getSelectedRow(), 0);

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Producto p WHERE p.nombre = :nombre");
        query.setParameter("nombre", nombreProducto);
        Producto producto = (Producto) query.uniqueResult();

        return producto;
    }

    private void listarProductos() {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Producto");
        List<Producto> listaProductos = query.list();
        llenarTabla(listaProductos);
    }

    private void listarProductos(String nombre) {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Producto p WHERE p.nombre = :nombre");
        query.setParameter("nombre", nombre);
        List<Producto> listaProductos = query.list();
        llenarTabla(listaProductos);
    }

    private void llenarTabla(List<Producto> listaProductos) {

        modeloTabla.setNumRows(0);
        for (Producto producto : listaProductos) {

            Object[] fila = new Object[]{producto.getNombre(),
                    producto.getDescripcion(),
                    Util.formatMoneda(producto.getPrecio())};
            modeloTabla.addRow(fila);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        String texto = tfBusquedaProducto.getText();
        if (texto.length() > 2) {
            listarProductos(texto);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

        if (e.getSource() == tfBusquedaProducto) {
            tfBusquedaProducto.selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
