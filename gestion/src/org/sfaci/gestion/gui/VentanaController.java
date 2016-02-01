package org.sfaci.gestion.gui;

import org.sfaci.gestion.base.Cliente;
import org.sfaci.gestion.base.DetallePedido;
import org.sfaci.gestion.base.Pedido;
import org.sfaci.gestion.base.Producto;
import org.sfaci.gestion.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controlador para la ventana
 */
public class VentanaController implements ActionListener {

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
                //cliente = lClientes.get
                cliente.setNombre(view.tfNombreCliente.getText());
                cliente.setApellidos(view.tfApellidos.getText());
                cliente.setEmail(view.tfEmail.getText());
                cliente.setTelefono(view.tfTelefono.getText());
                //model.modificarCliente(cliente);
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
                model.guardarPedido(numero, fecha, fechaEntrega, cliente);
                break;
            default:
                break;
        }
    }

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
