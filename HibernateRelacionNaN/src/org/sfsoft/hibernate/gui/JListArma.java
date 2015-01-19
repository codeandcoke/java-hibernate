package org.sfsoft.hibernate.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.sfsoft.hibernate.Arma;
import org.sfsoft.hibernate.util.HibernateUtil;

/**
 * Clase que muestra una lista de todas las armas disponibles
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class JListArma extends JList {

	private DefaultListModel modeloLista;
	
	JListArma() {
		super();
		
		modeloLista = new DefaultListModel();
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		setModel(modeloLista);
	}
	
	/**
	 * Lista todas las armas disponibles
	 */
	public void listar() {
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Arma");
		List<Arma> armas = query.list();
		
		removeAll();
		for (Arma arma : armas) {
			modeloLista.addElement(arma.getNombre());
		}
	}
	
	/*
	 * Devuelve la lista de los nombres de las armas seleccionadas
	 */
	private Object[] getNombreArmasSeleccionadas() {
		
		if (getSelectedIndex() == -1)
			return null;
		
		Object[] armasSeleccionadas = getSelectedValues();
		return armasSeleccionadas;
	}
	
	/**
	 * Devuelve la lista de las armas seleccionadas
	 * @return
	 */
	public List<Arma> getArmasSeleccionadas() {
		
		Object[] nombreArmasSeleccionadas = getNombreArmasSeleccionadas();
		if (nombreArmasSeleccionadas == null)
				return new ArrayList<Arma>();
		
		String consulta = "FROM Arma a WHERE a.nombre IN (";
		
		for (int i = 0; i < nombreArmasSeleccionadas.length; i++) {
			consulta += "'" + (String) nombreArmasSeleccionadas[i] + "'";
			if (i < nombreArmasSeleccionadas.length - 1)
				consulta += ", ";
		}
		
		consulta += ")";
		
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(consulta);
		List<Arma> armas = query.list();
	
		return armas;
	}
	
	/**
	 * Selecciona las armas que posee el personaje
	 * @param armas
	 */
	public void setArmasSeleccionadas(List<Arma> armas) {
		
		for (Arma arma : armas) {
			setSelectedValue(arma.getNombre(), false);
		}
	}
}
