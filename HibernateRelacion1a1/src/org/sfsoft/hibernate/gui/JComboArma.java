package org.sfsoft.hibernate.gui;

import java.util.List;

import javax.swing.JComboBox;

import org.hibernate.Query;
import org.sfsoft.hibernate.Arma;
import org.sfsoft.hibernate.util.HibernateUtil;

/**
 * Clase que muestra una lista de todas las armas disponibles
 * @author Santiago Faci
 * @version 1.0
 *
 */

public class JComboArma extends JComboBox {

	JComboArma() {
		super();
	}
	
	/**
	 * Lista todas las armas disponibles
	 */
	public void listar() {
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Arma");
		List<Arma> armas = query.list();
		
		removeAll();
		for (Arma arma : armas) {
			addItem(arma.getNombre());
		}
	}
	
	/**
	 * Devuelve el nombre del arma seleccionada
	 * @return
	 */
	public String getNombreArmaSeleccionada() {
		
		String nombre = (String) getSelectedItem();
		return nombre;
	}
	
	/**
	 * Devuelve el arma seleccionada
	 * @return
	 */
	public Arma getArmaSeleccionada() {
		
		String nombre = (String) getSelectedItem();
		if (nombre == null)
			return null;
		
		Query query = HibernateUtil.getCurrentSession().createQuery("SELECT a FROM Arma a WHERE a.nombre = :nombre");
		query.setParameter("nombre", nombre);
		Arma arma = (Arma) query.uniqueResult();
		
		return arma;
	}
}
