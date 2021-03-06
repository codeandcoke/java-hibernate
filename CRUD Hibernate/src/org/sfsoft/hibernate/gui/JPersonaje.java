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

/**
 * Dialog con el que el usuario introduce informaci�n sobre un Personaje
 * para insertar o modificar
 * @author Santiago Faci
 * @version 2.0
 */
public class JPersonaje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtVida;
	private JTextField txtPuntos;
	private JTextField txtArma;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	private String nombre;
	private int vida;
	private int puntos;
	private String arma;

	/**
	 * Getters y setters para obtener y fijar informaci�n en la ventana
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
	
	public String getArma() {
		return arma;
	}
	
	public void setArma(String arma) {
		this.txtArma.setText(arma);
	}
	
	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la informaci�n de las cajas de texto
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
			arma = txtArma.getText();
			
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
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JPersonaje() {
		setModal(true);
		setTitle("Personaje");
		setBounds(100, 100, 284, 253);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(100, 22, 86, 20);
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
		
		txtArma = new JTextField();
		txtArma.setBounds(100, 148, 86, 20);
		contentPanel.add(txtArma);
		txtArma.setColumns(10);
		
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
