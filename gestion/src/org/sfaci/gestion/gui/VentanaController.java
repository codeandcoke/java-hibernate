package org.sfaci.gestion.gui;

import org.sfaci.gestion.base.*;
import org.sfaci.gestion.util.Util;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controlador para la ventana
 */
public class VentanaController implements ActionListener, ChangeListener,
        MouseListener, FocusListener {

    private VentanaModel model;
    private Ventana view;

    private boolean nuevoCliente;

    public VentanaController(VentanaModel model, Ventana view) {
        this.model = model;
        this.view = view;

        addListeners();
        inicializar();
    }

    private void inicializar() {
        model.conectar();


        modoEdicionCliente(false, true);
        modoEdicionProducto(false, true);
        modoEdicionPedido(false, true);

        listarClientes();
    }

    private void modoEdicionCliente(boolean editable,
                                    boolean nuevo) {

        view.tfNombreCliente.setEditable(editable);
        view.tfApellidos.setEditable(editable);
        view.tfEmail.setEditable(editable);
        view.tfTelefono.setEditable(editable);

        if (nuevo) {
            view.tfNombreCliente.setText("");
            view.tfApellidos.setText("");
            view.tfEmail.setText("");
            view.tfTelefono.setText("");
        }
    }

    private void modoEdicionProducto(boolean editable,
                                     boolean nuevo) {


    }

    /**
     * Cambia el modo edicion/vista de la pestaña de clientes
     * @param editable Indica si las cajas deben ser editables
     * @param nuevo Indica si el texto de las cajas debe
     *              ser borrado
     */
    private void modoEdicionPedido(boolean editable,
                                   boolean nuevo) {

        view.tfNumeroPedido.setEditable(editable);
        view.dcFechaEntregaPedido.setEnabled(editable);
        view.dcFechaPedido.setEnabled(editable);

        if (nuevo) {
            view.tfNumeroPedido.setText("");
            view.dcFechaEntregaPedido.setDate(null);
            view.dcFechaPedido.setDate(null);
        }
    }

    /**
     * Añade los eventos a los controles
     */
    public void addListeners() {
        view.btCancelarCliente.addActionListener(this);
        view.btNuevoCliente.addActionListener(this);
        view.btGuardarCliente.addActionListener(this);
        view.btEliminarCliente.addActionListener(this);
        view.btModificarCliente.addActionListener(this);

        view.btGuardarProducto.addActionListener(this);

        view.btNuevoDetalle.addActionListener(this);
        view.btModificarDetalle.addActionListener(this);
        view.btEliminarDetalle.addActionListener(this);

        view.btNuevoPedido.addActionListener(this);
        view.btGuardarPedido.addActionListener(this);

        view.tbPanel.addChangeListener(this);

        view.lClientes.addMouseListener(this);
        view.lProductosCategoria.addMouseListener(this);

        view.btCrearCategoria.addActionListener(this);

        view.tfPrecioProducto.addFocusListener(this);
    }

    public void actionPerformed(ActionEvent event) {

        Cliente cliente = null;
        String actionCommand = event.getActionCommand();

        switch (actionCommand) {
            case "nuevoCliente":
                modoEdicionCliente(true, true);
                nuevoCliente = true;
                break;
            case "guardarCliente":
                if (nuevoCliente)
                    cliente = new Cliente();
                else
                    cliente = (Cliente) view.lClientes.getSelectedValue();

                cliente.setNombre(view.tfNombreCliente.getText());
                cliente.setApellidos(view.tfApellidos.getText());
                cliente.setEmail(view.tfEmail.getText());
                cliente.setTelefono(view.tfTelefono.getText());
                if (nuevoCliente)
                    model.guardarCliente(cliente);
                else {
                    if (Util.mensajeConfirmacion("Modificar", "¿Está seguro?") ==
                            JOptionPane.NO_OPTION)
                        return;

                    model.modificarCliente(cliente);
                }

                modoEdicionCliente(false, false);
                listarClientes();
                break;
            case "modificarCliente":
                nuevoCliente = false;
                modoEdicionCliente(true, false);
                break;
            case "eliminarCliente":
                if (Util.mensajeConfirmacion("Eliminar", "¿Está seguro?") ==
                        JOptionPane.NO_OPTION)
                    return;

                cliente = (Cliente) view.lClientes.getSelectedValue();
                model.eliminarCliente(cliente);
                listarClientes();
                break;
            case "cancelarCliente":
                break;
            case "guardarProducto":
                try {
                    float precio = Util.unFormatMoneda(
                            view.tfPrecioProducto.getText());
                    System.out.println(precio);
                } catch (ParseException pe) {
                    Util.mensajeError("Precio", "Precio no válido");
                }
                break;
            case "nuevoDetalle":
                JSeleccionProducto jProducto =
                        new JSeleccionProducto();
                jProducto.mostrarDialogo();

                int cantidad = jProducto.getCantidad();
                Producto producto = jProducto.getProducto();

                DetallePedido detalle = new DetallePedido();
                detalle.setUnidades(cantidad);
                detalle.setPrecio(cantidad * producto.getPrecio());
                detalle.setProducto(producto);

                model.nuevoDetalle(detalle);

                listarDetalles();

                break;
            case "modificarDetalle":
                break;
            case "eliminarDetalle":
                break;
            case "nuevoPedido":
                view.tfNumeroPedido.setText("");
                view.dcFechaEntregaPedido.setDate(null);
                view.dcFechaPedido.setDate(null);
                break;
            case "guardarPedido":

                String numero = view.tfNumeroPedido.getText();
                Date fecha = view.dcFechaPedido.getDate();
                Date fechaEntrega = view.dcFechaEntregaPedido.getDate();
                cliente = (Cliente) view.cbClientePedido.getSelectedItem();

                Pedido pedido = new Pedido();
                pedido.setNumero(numero);
                pedido.setFecha(fecha);
                pedido.setFechaEntrega(fechaEntrega);
                pedido.setCliente(cliente);

                model.guardarPedido(pedido);
                break;
            case "modificarPedido":

                break;
            case "eliminarPedido":
                if (Util.mensajeConfirmacion("Eliminar", "¿Está seguro?") ==
                        JOptionPane.NO_OPTION)
                    return;
                break;
            case "crearCategoria":
                Categoria categoria = new Categoria();
                categoria.setNombre(view.tfNombreCategoria.getText());
                categoria.setDescripcion(view.tfDescripcionCategoria.getText());

                List<Producto> listaProductos =
                        view.lProductosCategoria.getSelectedValuesList();

                model.crearCategoria(categoria, listaProductos);
                break;
            default:
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        int indice = view.tbPanel.getSelectedIndex();
        switch (indice) {
            case 0:
                listarClientes();
                break;
            case 1:
                listarProductos();
                break;
            case 2:
                listarPedidos();
                break;
            case 3:
                listarProductosCategoria();
                break;
            default:
                break;
        }
    }

    /**
     * Carga la lista de clientes en pantalla
     */
    private void listarClientes() {
        List<Cliente> listaClientes = model.getClientes();
        view.modeloListaClientes.removeAllElements();
        for (Cliente cliente : listaClientes) {
            view.modeloListaClientes.addElement(cliente);
        }
    }

    /**
     * Carga la lista de productos en pantalla
     */
    private void listarProductos() {

    }

    /**
     * Carga la lista de pedidos en pantalla
     */
    private void listarPedidos() {
        List<Pedido> listaPedidos = model.getPedidos();
        view.modeloListaPedidos.removeAllElements();
        for (Pedido pedido : listaPedidos) {
            view.modeloListaPedidos.addElement(pedido);
        }
    }

    /**
     * Lista los detalles de un Pedido en su tabla
     */
    private void listarDetalles() {
        ArrayList<DetallePedido> detalles= model.getDetalles();

        view.modeloTablaDetalles.setNumRows(0);
        for (DetallePedido detalle : detalles) {
            Object[] fila = new Object[]{
                    detalle.getProducto().getNombre(),
                    detalle.getUnidades(),
                    Util.formatMoneda(detalle.getProducto().getPrecio()),
                    Util.formatMoneda(detalle.getPrecio())
            };
            view.modeloTablaDetalles.addRow(fila);
        }
    }

    private void listarProductosCategoria() {
        List<Producto> listaProductos = model.getProductos();
        view.modeloListaProductosCategoria.removeAllElements();
        for (Producto producto : listaProductos) {
            view.modeloListaProductosCategoria.addElement(producto);
        }
    }

    /**
     * Carga los datos del cliente en el formulario
     * @param cliente
     */
    private void mostrarCliente(Cliente cliente) {

        view.tfNombreCliente.setText(cliente.getNombre());
        view.tfApellidos.setText(cliente.getApellidos());
        view.tfEmail.setText(cliente.getEmail());
        view.tfTelefono.setText(cliente.getTelefono());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == view.lClientes) {
            Cliente cliente = (Cliente) view.lClientes.getSelectedValue();
            mostrarCliente(cliente);
        }
        else if (e.getSource() == view.lProductosCategoria) {
            Producto producto = (Producto) view.lProductosCategoria.getSelectedValue();
            for (Categoria categoria : producto.getCategorias()) {
                System.out.println(categoria.getNombre());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {


    }
}
