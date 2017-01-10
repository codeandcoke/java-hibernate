package org.sfsoft.cine.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.sfsoft.cine.base.Actor;
import org.sfsoft.cine.base.Director;
import org.sfsoft.cine.base.Pelicula;
import org.sfsoft.hibernate.util.HibernateUtil;
import org.sfsoft.hibernate.util.Util;

import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ListSelectionModel;

/**
 * Dialog con el que el usuario introduce información sobre un Director
 * para insertar o modificar
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class JPelicula extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitulo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	
	private String titulo;
	private Date fechaEstreno;
	private JDateChooser tfFechaEstreno;
	
	private Util.Accion accion;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JList<Director> listDirectores;
	private JList<Actor> listActores;
	
	private DefaultListModel<Director> modeloListaDirectores;
	private DefaultListModel<Actor> modeloListaActores;
	private Director directorSeleccionado;
	private List<Actor> actoresSeleccionados;

	/**
	 * Getters y setters para obtener y fijar información en la ventana
	 * @return
	 */
	
	public String getNombre() {
		return titulo;
	}
	
	public void setNombre(String nombre) {
		this.tfTitulo.setText(nombre);
	}
	
	public Date getFechaNacimiento() {
		return fechaEstreno;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaEstreno = fechaNacimiento;
	}
	
	public Util.Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	public Pelicula getPelicula() {
		
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo(titulo);
		pelicula.setFechaEstreno(fechaEstreno);
		pelicula.setDirector(directorSeleccionado);
		pelicula.setActors(new HashSet<Actor>(actoresSeleccionados));
		
		return pelicula;
	}

	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (tfTitulo.getText().equals(""))
			return;

		titulo = tfTitulo.getText();
		fechaEstreno = tfFechaEstreno.getDate();
		
		directorSeleccionado = listDirectores.getSelectedValue();
		actoresSeleccionados = 
			listActores.getSelectedValuesList();
		
		accion = Util.Accion.ACEPTAR;
		setVisible(false);
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {
		accion = Util.Accion.CANCELAR;
		setVisible(false);
	}
	
	private void inicializar() {
		
		modeloListaDirectores = new DefaultListModel<Director>();
		listDirectores.setModel(modeloListaDirectores);
		modeloListaActores = new DefaultListModel<Actor>();
		listActores.setModel(modeloListaActores);		
		
		cargarDirectores();
		cargarActores();
	}
	
	private void cargarDirectores() {
		
		Query query = HibernateUtil.
			getCurrentSession().createQuery("FROM Director");
		List<Director> directores = query.list();
		
		for (Director director : directores) {
			modeloListaDirectores.addElement(director);
		}
	}
	
	private void cargarActores() {
		
		Query query = HibernateUtil.
				getCurrentSession().createQuery("FROM Actor");
			List<Actor> actores = query.list();
			
			for (Actor actor : actores) {
				modeloListaActores.addElement(actor);
			}
	}
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JPelicula() {
		setModal(true);
		setTitle("Película");
		setBounds(100, 100, 357, 483);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		tfTitulo = new JTextField();
		tfTitulo.setColumns(10);
		
		lblNewLabel = new JLabel("Título");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblNewLabel_2 = new JLabel("Fecha Estreno");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(tfTitulo, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(getTfFechaEstreno(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(getLblNewLabel_1(), GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(120)
							.addComponent(getLblNewLabel_3(), GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(getScrollPane_1(), GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(tfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(getTfFechaEstreno(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(getLblNewLabel_1())
						.addComponent(getLblNewLabel_3()))
					.addGap(11)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
						.addComponent(getScrollPane_1(), GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))
		);
		contentPanel.setLayout(gl_contentPanel);
		inicializar();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		inicializar();
	}
	
	public JDateChooser getTfFechaEstreno() {
		if (tfFechaEstreno == null) {
			tfFechaEstreno = new JDateChooser();
		}
		return tfFechaEstreno;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListDirectores());
		}
		return scrollPane;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getListActores());
		}
		return scrollPane_1;
	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Director");
		}
		return lblNewLabel_1;
	}
	public JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Actores");
		}
		return lblNewLabel_3;
	}
	public JList<Director> getListDirectores() {
		if (listDirectores == null) {
			listDirectores = new JList<Director>();
			listDirectores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listDirectores;
	}
	public JList<Actor> getListActores() {
		if (listActores == null) {
			listActores = new JList<Actor>();
		}
		return listActores;
	}
	
	
}
