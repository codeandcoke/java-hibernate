package org.sfaci.gestion.gui;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.sfaci.gestion.HibernateUtil;
import org.sfaci.gestion.base.Cliente;
import org.sfaci.gestion.base.DetallePedido;
import org.sfaci.gestion.base.Pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Modelo para la ventana
 */
public class VentanaModel {

    private ArrayList<DetallePedido> detalles;
    private Pedido pedido;

    public VentanaModel() {
        detalles = new ArrayList<>();
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public void conectar() {
        try {
            HibernateUtil.buildSessionFactory();
            HibernateUtil.openSession();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            HibernateUtil.closeSessionFactory();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void guardarCliente(Cliente cliente) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(cliente);
        sesion.getTransaction().commit();
    }

    public void modificarCliente() {

    }

    public void eliminarCliente() {

    }

    public ArrayList<Cliente> getClientes() {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        return clientes;
    }

    public void nuevoDetalle(DetallePedido detalle) {
        detalle.setPedido(pedido);
        pedido.getDetallesPedido().add(detalle);
        detalles.add(detalle);
    }

    public void nuevoPedido() {
        pedido = new Pedido();
    }

    public void guardarPedido(String numero, Date fecha,
        Date fechaEntrega, Cliente cliente) {

        pedido.setNumero(numero);
        pedido.setFecha(fecha);
        pedido.setFechaEntrega(fechaEntrega);
        pedido.setCliente(cliente);

        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(pedido);
        for (DetallePedido detalle : pedido.getDetallesPedido()) {
            sesion.save(detalle);
        }
        sesion.getTransaction().commit();
        sesion.close();
    }
}
