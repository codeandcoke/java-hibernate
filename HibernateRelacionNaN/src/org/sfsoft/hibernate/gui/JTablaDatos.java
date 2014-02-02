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
import org.sfsoft.hibernate.Enemigo;
import org.sfsoft.hibernate.util.HibernateUtil;


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
		modeloDatos.addColumn("armas");
		
		this.setModel(modeloDatos);
	}
	
	/**
	 * Muestra una lista de enemigos en la tabla
	 * @param enemigos
	 */
	public void listar(List<Enemigo> enemigos) {
		
		modeloDatos.setNumRows(0);
		for (Enemigo enemigo : enemigos) {
			
			Object[] fila = new Object[]{enemigo.getId(),
					enemigo.getNombre(), enemigo.getVida(), enemigo.getPuntos(), enemigo.getNombreArmas()};
			modeloDatos.addRow(fila);
		}
	}
	
	/**
	 * Obtiene el enemigo seleccionado de la tabla
	 * @return
	 */
	public Enemigo getEnemigoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		int id = (Integer) getValueAt(filaSeleccionada, 0);
		
		Enemigo enemigo = (Enemigo) HibernateUtil.getCurrentSession().get(Enemigo.class, id);
		return enemigo;
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
	
	public List<Arma> getArmasEnemigoSeleccionado() {
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if (filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 1);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("SELECT e.armas FROM Enemigo e WHERE e.nombre = :nombre");
		query.setParameter("nombre", nombre);
		List<Arma> armas = (List<Arma>) query.list();
		
		return armas;
	}
}
