package org.sfsoft.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.sfsoft.hibernate.Arma;
import org.sfsoft.hibernate.HibernateUtil;
import org.sfsoft.hibernate.Personaje;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Aplicación que muestra las operaciones CRUD
 * (Create, Read, Update, Delete) utilizando Relaciones entre tablas
 * que están relacionadas mediante una relación uno a muchos
 * @author Santiago Faci
 * @version curso 2014-2015
 */

public class RelacionesHibernate1aN {

	private JFrame frmRelacionesHibernate;
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

	private JPanel panelSuperior;
	private JButton btAlta;
	private JButton btEliminar;
	private JButton btModificar;
	private JPopupMenu popupMenu;
	private JMenuItem mntmConElMismo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelacionesHibernate1aN window = new RelacionesHibernate1aN();
					window.frmRelacionesHibernate.setLocationRelativeTo(null);
					window.frmRelacionesHibernate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RelacionesHibernate1aN() {
		initialize();
	}
	
	private void inicializar() {
		
		
	}
	
	/**
	 * Inicializa la conexión con la Base de Datos
	 * Hay que tener en cuenta que la configuración de conexión se encuentra en el fichero hibernate.cfg.xml
	 */
	private void conectar() {
		
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSession();
		
			cargarDatos();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}
	
	/**
	 * Libera los recursos de la conexión
	 */
	private void desconectar() {
		
		try {
			HibernateUtil.closeSessionFactory();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}
	
	/**
	 * Sale de la aplicación
	 */
	private void salir() {

		desconectar();
		System.exit(0);
	}
	
	/**
	 * Carga el contenido de la tabla en un JTable utilizando una Query (HQL)
	 */
	private void cargarDatos() {
		
		// Prepara y ejecuta la consulta
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Personaje");
		List<Personaje> personajes = query.list();
		// Muestra la lista en la JTable
		tabla.listar(personajes);
		lbTotal.setText("Personajes: " + personajes.size());
	}
	
	/*
	 * Da de alta un nuevo personaje
	 */
	private void nuevo() {
		JPersonaje jPersonaje = new JPersonaje();
		jPersonaje.setVisible(true);
		
		Personaje personaje = new Personaje();
		personaje.setNombre(jPersonaje.getNombre());
		personaje.setNivel(jPersonaje.getNivel());
		personaje.setEnergia(jPersonaje.getEnergia());
		personaje.setPuntos(jPersonaje.getPuntos());
		personaje.setArma(jPersonaje.getArma());
		
		// Da de alta el objeto en la tabla de datos
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.save(personaje);
		//session.saveOrUpdate(personaje);
		session.getTransaction().commit();
		session.close();
		
		cargarDatos();
	}
	
	/*
	 * Modifica el personaje seleccionado
	 */
	private void modificar() {
		
		Personaje personaje = tabla.getPersonajeSeleccionado();
		if (personaje == null)
			return;
		
		JPersonaje jPersonaje = new JPersonaje();
		jPersonaje.setNombre(personaje.getNombre());
		jPersonaje.setNivel(personaje.getNivel());
		jPersonaje.setEnergia(personaje.getEnergia());
		jPersonaje.setPuntos(personaje.getPuntos());
		jPersonaje.setArma(personaje.getArma().getNombre());
		jPersonaje.setVisible(true);
		
		personaje.setNombre(jPersonaje.getNombre());
		personaje.setNivel(jPersonaje.getNivel());
		personaje.setEnergia(jPersonaje.getEnergia());
		personaje.setPuntos(jPersonaje.getPuntos());
		personaje.setArma(jPersonaje.getArma());
		
		// Almacena los cambios en la tabla
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.update(personaje);
		//session.saveOrUpdate(personaje);
		session.getTransaction().commit();
		session.close();
		
		cargarDatos();
	}
	
	/*
	 * Elimina el personaje seleccionado
	 */
	private void eliminar() {
		
		Personaje personaje = tabla.getPersonajeSeleccionado();
		if (personaje == null)
			return;
		
		// Elimina el personaje de la tabla de datos
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.delete(personaje);
		session.getTransaction().commit();
		session.close();
		
		cargarDatos();
	}
	
	/*
	 * Muestra los personajes que poseen el arma del personaje seleccionado
	 */
	private void verPersonajeMismaArma() {
		
		Arma arma = tabla.getArmaSeleccionada();
		if (arma == null)
			return;
		
		JArma jArma = new JArma();
		jArma.setArma(arma);
		jArma.mostrar();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRelacionesHibernate = new JFrame();
		frmRelacionesHibernate.setTitle("Relaciones Hibernate 1 a N\n");
		frmRelacionesHibernate.setBounds(100, 100, 450, 300);
		frmRelacionesHibernate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmRelacionesHibernate.setJMenuBar(menuBar);
		
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
		frmRelacionesHibernate.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		lbTotal = new JLabel("Personajes: 0\n");
		panelInferior.add(lbTotal);
		
		panelCentral = new JPanel();
		frmRelacionesHibernate.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		mntmConElMismo = new JMenuItem("Con la misma arma . . .");
		mntmConElMismo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verPersonajeMismaArma();
			}
		});
		popupMenu.add(mntmConElMismo);
		
		tabla = new JTablaDatos();
		scrollPane.setViewportView(tabla);
		
		panelSuperior = new JPanel();
		frmRelacionesHibernate.getContentPane().add(panelSuperior, BorderLayout.NORTH);
		
		btAlta = new JButton("Nuevo");
		btAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		panelSuperior.add(btAlta);
		
		btEliminar = new JButton("Eliminar");
		btEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btModificar = new JButton("Modificar");
		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		panelSuperior.add(btModificar);
		panelSuperior.add(btEliminar);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
