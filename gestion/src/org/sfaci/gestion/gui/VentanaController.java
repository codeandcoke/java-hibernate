package org.sfaci.gestion.gui;

import org.sfaci.gestion.base.Cliente;
import org.sfaci.gestion.base.DetallePedido;
import org.sfaci.gestion.base.Pedido;
import org.sfaci.gestion.base.Producto;
import org.sfaci.gestion.util.Util;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controlador para la ventana
 */
public class VentanaController implements ActionListener, ChangeListener {

    private VentanaModel model;
    private Ventana view;

    public VentanaController(VentanaModel model, Ventana view) {
        this.model = model;
        this.view = view;

        addListeners();
        inicializar();
    }

    private void inicializar() {
        model.conectar();
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

        view.btNuevoDetalle.addActionListener(this);
        view.btModificarDetalle.addActionListener(this);
        view.btEliminarDetalle.addActionListener(this);

        view.btNuevoPedido.addActionListener(this);
        view.btGuardarPedido.addActionListener(this);

        view.tbPanel.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent event) {

        Cliente cliente = null;
        String actionCommand = event.getActionCommand();

        switch (actionCommand) {
            case "nuevoCliente":
                view.tfNombreCliente.setText("");
                view.tfNombreCliente.setEditable(true);
                view.tfApellidos.setText("");
                view.tfApellidos.setEditable(true);
                view.tfEmail.setText("");
                view.tfEmail.setEditable(true);
                view.tfTelefono.setText("");
                view.tfTelefono.setEditable(true);
                break;
            case "guardarCliente":
                cliente = new Cliente();
                cliente.setNombre(view.tfNombreCliente.getText());
                cliente.setApellidos(view.tfApellidos.getText());
                cliente.setEmail(view.tfEmail.getText());
                cliente.setTelefono(view.tfTelefono.getText());
                model.guardarCliente(cliente);
                break;
            case "modificarCliente":
                cliente = model.getCliente((String) view.lClientes.getSelectedValue());
                cliente.setNombre(view.tfNombreCliente.getText());
                cliente.setApellidos(view.tfApellidos.getText());
                cliente.setEmail(view.tfEmail.getText());
                cliente.setTelefono(view.tfTelefono.getText());
                model.modificarCliente(cliente);
                break;
            case "eliminarCliente":
                break;
            case "cancelarCliente":
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
            default:
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        int indice = view.tbPanel.getSelectedIndex();
        switch (indice) {
            case 0:
                List<Cliente> listaClientes = model.getClientes();
                for (Cliente cliente : listaClientes) {
                    view.modeloListaClientes.addElement(cliente);
                }
                break;
            case 1:
                break;
            case 2:
                List<Pedido> listaPedidos = model.getPedidos();
                for (Pedido pedido : listaPedidos) {
                    view.modeloListaPedidos.addElement(pedido);
                }
                break;
            default:
                break;
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
}
