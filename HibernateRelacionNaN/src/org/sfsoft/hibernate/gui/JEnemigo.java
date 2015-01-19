package org.sfsoft.hibernate.gui;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;

import org.sfsoft.hibernate.Arma;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Dialogo con el que el usuario introduce información sobre un Enemigo
 * para insertar o modificar
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class JEnemigo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtVida;
	private JTextField txtPuntos;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	private String nombre;
	private int vida;
	private int puntos;
	private List<Arma> armas;
	private JScrollPane scrollPane;
	private JListArma listaArmas;

	/**
	 * Getters y setters para obtener y fijar información en la ventana
	 * @return
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.txtNombre.setText(nombre);
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.txtVida.setText(String.valueOf(vida));
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.txtPuntos.setText(String.valueOf(puntos));
	}
	
	public List<Arma> getArmas() {
		return armas;
	}
	
	public void setArmas(List<Arma> armas) {
		listaArmas.setArmasSeleccionadas(armas);
	}
	
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (txtNombre.getText().equals(""))
			return;
		
		try {
			if (txtVida.getText().equals(""))
				txtVida.setText("0");
			if (txtPuntos.getText().equals(""))
				txtPuntos.setText("0");
			
			nombre = txtNombre.getText();
			vida = Integer.parseInt(txtVida.getText());
			puntos = Integer.parseInt(txtPuntos.getText());
			armas = listaArmas.getArmasSeleccionadas();
	
			setVisible(false);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Comprueba que los datos son correctos", "Enemigo", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Invocado cuando el usuario cancela. Simplemente cierra la ventana
	 */
	private void cancelar() {
		setVisible(false);
	}
	
	private void inicializar() {
		listaArmas.listar();
	}
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JEnemigo() {
		setModal(true);
		setTitle("Enemigo");
		setBounds(100, 100, 284, 355);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(100, 22, 138, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtVida = new JTextField();
		txtVida.setBounds(100, 64, 60, 20);
		contentPanel.add(txtVida);
		txtVida.setColumns(10);
		
		txtPuntos = new JTextField();
		txtPuntos.setBounds(100, 106, 60, 20);
		contentPanel.add(txtPuntos);
		txtPuntos.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 25, 46, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Vida");
		lblNewLabel_1.setBounds(10, 67, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Puntos");
		lblNewLabel_2.setBounds(10, 109, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Arma");
		lblNewLabel_3.setBounds(10, 151, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 151, 156, 125);
		contentPanel.add(scrollPane);
		
		listaArmas = new JListArma();
		scrollPane.setViewportView(listaArmas);
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
	}
}
