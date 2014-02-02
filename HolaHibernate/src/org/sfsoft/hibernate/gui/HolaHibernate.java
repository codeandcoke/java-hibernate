package org.sfsoft.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.sfsoft.hibernate.Personaje;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Aplicación que carga el contenido de una tabla de MySQL utilizando Hibernate
 * @author Santiago Faci
 *
 */

public class HolaHibernate {

	private JFrame frmHolaHibernate;
	private JMenuBar menuBar;
	private JMenu mnFichero;
	private JMenuItem mntmConectar;
	private JMenuItem mntmDesconectar;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JPanel panelInferior;
	private JPanel panelCentral;
	private JScrollPane scrollPane;
	private JTablaDatos tabla;
	private JLabel lbTotal;
	
	private SessionFactory sessionFactory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HolaHibernate window = new HolaHibernate();
					window.frmHolaHibernate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HolaHibernate() {
		initialize();
	}
	
	private void inicializar() {
		
	}
	
	/**
	 * Inicializa la conexión con la Base de Datos
	 * Hay que tener en cuenta que la configuración de conexión se encuentra en el fichero hibernate.cfg.xml
	 */
	private void conectar() {
		
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
						configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		cargarDatos();
	}
	
	/**
	 * Libera los recursos de la conexi�n
	 */
	private void desconectar() {
		
		sessionFactory.close();
		sessionFactory = null;
	}
	
	/**
	 * Sale de la aplicaci�n
	 */
	private void salir() {
		
		if (sessionFactory != null)
			desconectar();
		
		System.exit(0);
	}
	
	/**
	 * Carga el contenido de la tabla en un JTable utilizando una Query (HQL)
	 */
	private void cargarDatos() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("FROM Personaje");
		List<Personaje> personajes = query.list();
		tabla.listar(personajes);
		lbTotal.setText("Personajes: " + personajes.size());
		
		session.close();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHolaHibernate = new JFrame();
		frmHolaHibernate.setTitle("Hola Hibernate\n");
		frmHolaHibernate.setBounds(100, 100, 450, 300);
		frmHolaHibernate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmHolaHibernate.setJMenuBar(menuBar);
		
		mnFichero = new JMenu("Fichero");
		menuBar.add(mnFichero);
		
		mntmConectar = new JMenuItem("Conectar");
		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectar();
			}
		});
		mnFichero.add(mntmConectar);
		
		mntmDesconectar = new JMenuItem("Desconectar");
		mnFichero.add(mntmDesconectar);
		
		mntmSalir = new JMenuItem("Salir");
		mnFichero.add(mntmSalir);
		
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmAcercaDe = new JMenuItem("Acerca de");
		mnAyuda.add(mntmAcercaDe);
		
		panelInferior = new JPanel();
		frmHolaHibernate.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		lbTotal = new JLabel("Personajes: 0\n");
		panelInferior.add(lbTotal);
		
		panelCentral = new JPanel();
		frmHolaHibernate.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		tabla = new JTablaDatos();
		scrollPane.setViewportView(tabla);
	}

}
