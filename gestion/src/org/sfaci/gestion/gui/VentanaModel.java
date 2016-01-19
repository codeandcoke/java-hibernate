package org.sfaci.gestion.gui;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.sfaci.gestion.HibernateUtil;
import org.sfaci.gestion.base.Cliente;

import java.util.ArrayList;

/**
 * Modelo para la ventana
 */
public class VentanaModel {

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
}
