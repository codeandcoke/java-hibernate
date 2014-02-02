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
import javax.swing.JComboBox;

import org.sfsoft.hibernate.Arma;

/**
 * Dialog con el que el usuario introduce informaci—n sobre un Personaje
 * para insertar o modificar
 * @author Santiago Faci
 * @version 2.0
 */
public class JPersonaje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfNivel;
	private JTextField tfPuntos;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JComboArma cbArma;
	
	private String nombre;
	private int nivel;
	private int energia;
	private int puntos;
	private String arma;
	private JTextField tfEnergia;
	private JLabel lblEnerga;

	/**
	 * Getters y setters para obtener y fijar información en la ventana
	 * @return
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.tfNombre.setText(nombre);
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void setNivel(int nivel) {
		this.tfNivel.setText(String.valueOf(nivel));
	}
	
	public int getEnergia() {
		return energia;
	}
	
	public void setEnergia(int energia) {
		this.tfEnergia.setText(String.valueOf(energia));
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.tfPuntos.setText(String.valueOf(puntos));
	}
	
	public Arma getArma() {
		return cbArma.getArmaSeleccionada();
	}
	
	public void setArma(String arma) {
		this.cbArma.setSelectedItem(arma);
	}
	
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (tfNombre.getText().equals(""))
			return;
		
		try {
			if (tfNivel.getText().equals(""))
				tfNivel.setText("0");
			if (tfPuntos.getText().equals(""))
				tfPuntos.setText("0");
			if (tfEnergia.getText().equals(""))
				tfEnergia.setText("0");
			
			nombre = tfNombre.getText();
			nivel = Integer.parseInt(tfNivel.getText());
			energia = Integer.parseInt(tfEnergia.getText());
			puntos = Integer.parseInt(tfPuntos.getText());
			arma = (String) cbArma.getSelectedItem();
			
			setVisible(false);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Comprueba que los datos son correctos", "Personaje", 
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
		cbArma.listar();
	}
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JPersonaje() {
		setModal(true);
		setTitle("Personaje");
		setBounds(100, 100, 300, 291);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(100, 22, 138, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNivel = new JTextField();
		tfNivel.setBounds(100, 64, 60, 20);
		contentPanel.add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPuntos = new JTextField();
		tfPuntos.setBounds(100, 142, 60, 20);
		contentPanel.add(tfPuntos);
		tfPuntos.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 25, 46, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nivel");
		lblNewLabel_1.setBounds(10, 67, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Puntos");
		lblNewLabel_2.setBounds(10, 145, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Arma");
		lblNewLabel_3.setBounds(10, 187, 46, 14);
		contentPanel.add(lblNewLabel_3);
		
		cbArma = new JComboArma();
		inicializar();
		cbArma.setBounds(100, 182, 138, 27);
		contentPanel.add(cbArma);
		contentPanel.add(getTfEnergia());
		contentPanel.add(getLblEnerga());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btOk = new JButton("OK");
				btOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						aceptar();
					}
				});
				btOk.setActionCommand("OK");
				buttonPane.add(btOk);
				getRootPane().setDefaultButton(btOk);
			}
			{
				JButton btCancelar = new JButton("Cancel");
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				btCancelar.setActionCommand("Cancel");
				buttonPane.add(btCancelar);
			}
		}
	}
	public JTextField getTfEnergia() {
		if (tfEnergia == null) {
			tfEnergia = new JTextField();
			tfEnergia.setColumns(10);
			tfEnergia.setBounds(100, 100, 60, 20);
		}
		return tfEnergia;
	}
	public JLabel getLblEnerga() {
		if (lblEnerga == null) {
			lblEnerga = new JLabel("Energ\u00EDa");
			lblEnerga.setBounds(10, 103, 46, 14);
		}
		return lblEnerga;
	}
}
