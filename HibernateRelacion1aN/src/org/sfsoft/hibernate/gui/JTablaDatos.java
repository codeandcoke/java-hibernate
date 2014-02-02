package org.sfsoft.hibernate.gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.sfsoft.hibernate.Arma;
import org.sfsoft.hibernate.HibernateUtil;
import org.sfsoft.hibernate.Personaje;


/**
 * Clase que muestra una lista en una JTable
 * @author Santiago Faci
 * @version 2.0
 */
public class JTablaDatos extends JTable {
	
	private Connection conexion;
	private DefaultTableModel modeloDatos;
	
	private static final boolean DEBUG = false; 
	
	public JTablaDatos() {
		super();

		inicializar();
	}
	
	/**
	 * Inicializa la estructura de la tabla
	 */
	private void inicializar() {
		
		modeloDatos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		
		modeloDatos.addColumn("#");
		modeloDatos.addColumn("nombre");
		modeloDatos.addColumn("nivel");
		modeloDatos.addColumn("energia");
		modeloDatos.addColumn("puntos");
		modeloDatos.addColumn("arma");
		
		this.setModel(modeloDatos);
	}
	
	/**
	 * Muestra una lista de personajes en la tabla
	 * @param personajes
	 */
	public void listar(List<Personaje> personajes) {
		
		modeloDatos.setNumRows(0);
		for (Personaje personaje : personajes) {
			
			Object[] fila = new Object[]{personaje.getId(),
					personaje.getNombre(), personaje.getNivel(), personaje.getEnergia(), personaje.getPuntos(), personaje.getArma().getNombre()};
			modeloDatos.addRow(fila);
		}
	}
	
	/**
	 * Obtiene el personaje seleccionado de la tabla
	 * @return
	 */
	public Personaje getPersonajeSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Personaje personaje = (Personaje) HibernateUtil.getCurrentSession().get(Personaje.class, id);
		return personaje;
	}
	
	public Arma getArmaSeleccionada() {
		
int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 4);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("SELECT a FROM Arma a WHERE a.nombre = :nombre");
		query.setParameter("nombre", nombre);
		Arma arma = (Arma) query.uniqueResult();
		
		return arma;
	}
}
