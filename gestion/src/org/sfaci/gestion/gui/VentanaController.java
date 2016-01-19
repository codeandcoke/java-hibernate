package org.sfaci.gestion.gui;

import org.sfaci.gestion.base.DetallePedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    public void actionPerformed(ActionEvent event) {

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

                break;
            case "modificarCliente":
                break;
            case "eliminarCliente":
                break;
            case "cancelarCliente":
                break;
            case "nuevoDetalle":
                JSeleccionProducto jProducto =
                        new JSeleccionProducto();
                jProducto.mostrarDialogo();

                DetallePedido detalle = new DetallePedido();
                detalle.setUnidades(jProducto.getCantidad());
                detalle.setProducto(jProducto.getProducto());

                listaDetalles.add(detalle);

                break;
            case "modificarDetalle":
                break;
            case "eliminarDetalle":
                break;
            default:
                break;
        }
    }
}
