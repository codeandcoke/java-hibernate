package org.sfsoft.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sfsoft.hibernate.Arma;
import org.sfsoft.hibernate.Personaje;
import org.sfsoft.hibernate.util.HibernateUtil;

/**
 * Aplicaci—n que muestra las operaciones CRUD
 * (Create, Read, Update, Delete) utilizando Relaciones entre tablas
 * que est‡n relacionadas mediante una relaci—n muchos a muchos
 * @author Santiago Faci
 * @version 1.0
 */

public class RelacionesHibernate3 {

	private JFrame frmRelacionesHibernate;
	private JMenuBar menuBar;
	private JMenu mnFichero;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JPanel panelInferior;
	private JPanel panelCentral;
	private JScrollPane scrollPane;
	private JTablaDatos tabla;
	private JLabel lbTotal;
	
	private SessionFactory sessionFactory;
	private JPanel panelSuperior;
	private JButton btNuevoPersonaje;
	private JButton btEliminarPersonaje;
	private JButton btModificarPersonaje;
	private JPopupMenu popupMenu;
	private JMenuItem mntmConElMismo;
	private JPanel panelArmas;
	private JPanel panelPersonajes;
	private JButton btNuevaArma;
	private JButton btModificarArma;
	private JButton btEliminarArma;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelacionesHibernate3 window = new RelacionesHibernate3();
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
	public RelacionesHibernate3() {
		initialize();
		inicializar();
	}
	
	private void inicializar() {
		
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSession();
		
			cargarDatos();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}
	
	/**
	 * Libera los recursos de la conexi—n
	 */
	private void desconectar() {
		
		try {
			HibernateUtil.closeSessionFactory();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}
	
	/**
	 * Sale de la aplicaci—n
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
	private void nuevoPersonaje() {
		JPersonaje jPersonaje = new JPersonaje();
		jPersonaje.setVisible(true);
		
		Personaje personaje = new Personaje();
		personaje.setNombre(jPersonaje.getNombre());
		personaje.setVida(jPersonaje.getVida());
		personaje.setPuntos(jPersonaje.getPuntos());
		personaje.getArmas().addAll(jPersonaje.getArmas());
		
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
	private void modificarPersonaje() {
		
		Personaje personaje = tabla.getPersonajeSeleccionado();
		if (personaje == null)
			return;
		
		JPersonaje jPersonaje = new JPersonaje();
		jPersonaje.setNombre(personaje.getNombre());
		jPersonaje.setVida(personaje.getVida());
		jPersonaje.setPuntos(personaje.getPuntos());
		jPersonaje.setArmas(personaje.getArmas());
		jPersonaje.setVisible(true);
		
		personaje.setNombre(jPersonaje.getNombre());
		personaje.setVida(jPersonaje.getVida());
		personaje.setPuntos(jPersonaje.getPuntos());
		personaje.setArmas(jPersonaje.getArmas());
		
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
	private void eliminarPersonaje() {
		
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
		
		JListadoArma jArma = new JListadoArma();
		jArma.setArma(arma);
		jArma.mostrar();
	}
	
	/*
	 * Da de alta una nueva arma
	 */
	private void nuevaArma() {
		
		JArma jArma = new JArma();
		jArma.setVisible(true);
		
		Arma arma = new Arma();
		arma.setNombre(jArma.getNombre());
		arma.setDescripcion(jArma.getDescripcion());
		arma.setDano(jArma.getDano());
		
		// Da de alta el objeto en la tabla de datos
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.save(arma);
		//session.saveOrUpdate(arma);
		session.getTransaction().commit();
		session.close();
		
		cargarDatos();
	}
	
	/*
	 * Modifica un arma
	 * TODO
	 */
	private void modificarArma() {
		
	}
	
	/*
	 * Elimina un arma
	 * TODO
	 */
	private void eliminarArma() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRelacionesHibernate = new JFrame();
		frmRelacionesHibernate.setTitle("Hola Hibernate\n");
		frmRelacionesHibernate.setBounds(100, 100, 450, 300);
		frmRelacionesHibernate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmRelacionesHibernate.setJMenuBar(menuBar);
		
		mnFichero = new JMenu("Fichero");
		menuBar.add(mnFichero);
		
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
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		panelArmas = new JPanel();
		panelSuperior.add(panelArmas, BorderLayout.SOUTH);
		
		lblNewLabel_1 = new JLabel("Armas");
		panelArmas.add(lblNewLabel_1);
		
		btNuevaArma = new JButton("Nueva");
		btNuevaArma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaArma();
			}
		});
		panelArmas.add(btNuevaArma);
		
		btModificarArma = new JButton("Modificar");
		btModificarArma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarArma();
			}
		});
		panelArmas.add(btModificarArma);
		
		btEliminarArma = new JButton("Eliminar");
		btEliminarArma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarArma();
			}
		});
		panelArmas.add(btEliminarArma);
		
		panelPersonajes = new JPanel();
		panelSuperior.add(panelPersonajes, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Personajes");
		panelPersonajes.add(lblNewLabel);
		
		btNuevoPersonaje = new JButton("Nuevo");
		panelPersonajes.add(btNuevoPersonaje);
		
		btModificarPersonaje = new JButton("Modificar");
		panelPersonajes.add(btModificarPersonaje);
		
		btEliminarPersonaje = new JButton("Eliminar");
		panelPersonajes.add(btEliminarPersonaje);
		btEliminarPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPersonaje();
			}
		});
		btModificarPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarPersonaje();
			}
		});
		btNuevoPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoPersonaje();
			}
		});
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
