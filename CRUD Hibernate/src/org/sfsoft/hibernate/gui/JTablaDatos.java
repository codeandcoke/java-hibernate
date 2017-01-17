package org.sfsoft.hibernate.gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		modeloDatos.addColumn("vida");
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
					personaje.getNombre(), personaje.getVida(), personaje.getPuntos(), personaje.getArma()};
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
		
		Personaje personaje = new Personaje();
		personaje.setId((Integer) getValueAt(filaSeleccionada, 0));
		personaje.setNombre((String) getValueAt(filaSeleccionada, 1));
		personaje.setVida((Integer) getValueAt(filaSeleccionada, 2));
		personaje.setPuntos((Integer) getValueAt(filaSeleccionada, 3));
		personaje.setArma((String) getValueAt(filaSeleccionada, 4));
		
		return personaje;
	}
}
