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
 * @version 1.0
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
			
			Object[] fila = new Object[]{personaje.getNombre(), personaje.getVida(), personaje.getPuntos(), personaje.getArma()};
			modeloDatos.addRow(fila);
		}
	}
}
