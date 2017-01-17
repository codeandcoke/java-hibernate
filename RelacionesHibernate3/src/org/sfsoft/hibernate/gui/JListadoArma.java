package org.sfsoft.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
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
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;

import org.sfsoft.hibernate.Arma;
import org.sfsoft.hibernate.Personaje;

import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Dialog para mostrar quŽ personajes poseen un arma determinada
 * @author Santiago Faci
 * @version 1.0
 */
public class JListadoArma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JLabel lbNombre;
	
	private Arma arma;
	private JScrollPane scrollPane;
	private JList listaPersonajes;
	private DefaultListModel modeloLista;
	
	public void setArma(Arma arma) {
		this.arma = arma;
	}

	
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		setVisible(false);
	}
	
	/**
	 * Muestra los personajes que poseen el arma seleccionada en una lista
	 */
	public void mostrar() {
		
		setVisible(true);
		
		modeloLista = new DefaultListModel();
		listaPersonajes.setModel(modeloLista);
		
		lbNombre.setText(arma.getNombre());
		
		List<Personaje> personajes = arma.getPersonajes();
		for (Personaje personaje : personajes) {
			
			modeloLista.addElement(personaje.getNombre());
		}
	}
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JListadoArma() {
		setTitle("Arma");
		setBounds(100, 100, 284, 253);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lbNombre = new JLabel("");
		lbNombre.setBounds(83, 33, 124, 16);
		contentPanel.add(lbNombre);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 68, 203, 118);
		contentPanel.add(scrollPane);
		
		listaPersonajes = new JList();
		scrollPane.setViewportView(listaPersonajes);
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
		}
	}
}
