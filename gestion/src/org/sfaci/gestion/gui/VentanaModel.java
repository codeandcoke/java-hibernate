package org.sfaci.gestion.gui;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.sfaci.gestion.HibernateUtil;
import org.sfaci.gestion.base.Cliente;
import org.sfaci.gestion.base.DetallePedido;
import org.sfaci.gestion.base.Pedido;
import org.sfaci.gestion.base.Producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Modelo para la ventana
 */
public class VentanaModel {

    private ArrayList<DetallePedido> detalles;

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

    /**
     * Registra un cliente en la base de datos
     * @param cliente
     */
    public void guardarCliente(Cliente cliente) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(cliente);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Modifica un cliente
     * @param cliente
     */
    public void modificarCliente(Cliente cliente) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.update(cliente);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Elimina un cliente de la Base de Datos
     */
    public void eliminarCliente(Cliente cliente) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.delete(cliente);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Obtiene un cliente a partir de su nombre
     * @param nombre
     * @return
     */
    public Cliente getCliente(String nombre) {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Cliente c WHERE c.nombre = :nombre");
        query.setParameter("nombre", nombre);
        Cliente cliente = (Cliente) query.uniqueResult();

        return cliente;
    }

    /**
     * Obtiene el listado de todos los clientes
     * @return
     */
    public ArrayList<Cliente> getClientes() {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Cliente");
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) query.list();

        return clientes;
    }

    /**
     * Registra un producto en la base de datos
     * @param producto
     */
    public void guardarProducto(Producto producto) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(producto);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Modifica un producto
     * @param producto
     */
    public void modificarProducto(Producto producto) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.update(producto);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Elimina un producto de la Base de Datos
     */
    public void eliminarProducto(Producto producto) {
        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.delete(producto);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Obtiene un producto a partir de su nombre
     * @param nombre
     * @return
     */
    public Producto getProducto(String nombre) {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Producto p WHERE p.nombre = :nombre");
        query.setParameter("nombre", nombre);
        Producto producto = (Producto) query.uniqueResult();

        return producto;
    }

    /**
     * Obtiene el listado de todos los productos
     * @return
     */
    public ArrayList<Producto> getProductos() {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Producto");
        ArrayList<Producto> productos = (ArrayList<Producto>) query.list();

        return productos;
    }


    /**
     * Añade un nuevo detalle a la lista temporal de detalles
     * del pedido en curso
     * @param detalle
     */
    public void nuevoDetalle(DetallePedido detalle) {
        detalles.add(detalle);
    }

    /**
     * Obtiene los detalles de un Pedido
     * @param numeroPedido El número de pedido
     * @return
     */
    public ArrayList<DetallePedido> getDetalles(String numeroPedido) {

        Query query = HibernateUtil.getCurrentSession().
            createQuery("FROM DetallePedido dp " +
                    "WHERE dp.pedido.numeroPedido = :numeroPedido");
        query.setParameter("numeroPedido", numeroPedido);

        ArrayList<DetallePedido> detalles =
                (ArrayList<DetallePedido>) query.list();

        return detalles;
    }

    /**
     * Registra un nuevo pedido en la Base de Datos
     * @param pedido
     */
    public void guardarPedido(Pedido pedido) {

        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.save(pedido);
        for (DetallePedido detalle : detalles) {
            detalle.setPedido(pedido);
            pedido.getDetallesPedido().add(detalle);
            sesion.save(detalle);
        }
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Modifica un pedido en la Base de Datos
     * @param pedido
     */
    public void modificarPedido(Pedido pedido) {

        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.update(pedido);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Elimina un pedido de la Base de Datos
     * @param pedido
     */
    public void eliminarPedido(Pedido pedido) {

        Session sesion = HibernateUtil.getCurrentSession();
        sesion.beginTransaction();
        sesion.delete(pedido);
        sesion.getTransaction().commit();
        sesion.close();
    }

    /**
     * Obtiene todos los pedidos de la Base de Datos
     * @return
     */
    public ArrayList<Pedido> getPedidos() {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Pedido");
        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) query.list();

        return pedidos;
    }

    /**
     * Obtiene un pedido
     * @param numero El número de pedido
     * @return
     */
    public Pedido getPedido(String numero) {

        Query query = HibernateUtil.getCurrentSession().
                createQuery("FROM Pedido p WHERE p.numero = :numero");
        query.setParameter("numero", numero);

        Pedido pedido = (Pedido) query.uniqueResult();

        return pedido;
    }
}
