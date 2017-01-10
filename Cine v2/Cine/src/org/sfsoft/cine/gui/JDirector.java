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

import javax.swing.JComboBox;

import org.sfsoft.cine.base.Actor;
import org.sfsoft.cine.base.Director;
import org.sfsoft.hibernate.util.Util;

import com.toedter.calendar.JDateChooser;

/**
 * Dialog con el que el usuario introduce información sobre un Director
 * para insertar o modificar
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class JDirector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String genero;
	private float sueldo;
	private JDateChooser tfFechaNacimiento;
	
	private Util.Accion accion;

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
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Util.Accion mostrarDialogo() {
		setVisible(true);
		
		return accion;
	}
	
	public Director getDirector() {
		
		Director director = new Director();
		director.setNombre(nombre);
		director.setFechaNacimiento(fechaNacimiento);
		
		return director;
	}

	/**
	 * Se invoca cuando el usuario ha pulsado en Aceptar. Recoge y valida la información de las cajas de texto
	 * y cierra la ventana
	 */
	private void aceptar() {
		
		if (tfNombre.getText().equals(""))
			return;

		nombre = tfNombre.getText();
		fechaNacimiento = tfFechaNacimiento.getDate();
		
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
	}
	
	/**
	 * Constructor. Crea la ventana
	 */
	public JDirector() {
		setModal(true);
		setTitle("Director");
		setBounds(100, 100, 276, 176);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(100, 22, 138, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 25, 46, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Fecha Nacimiento");
		lblNewLabel_2.setBounds(10, 53, 89, 14);
		contentPanel.add(lblNewLabel_2);
		contentPanel.add(getTfFechaNacimiento());
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
	public JDateChooser getTfFechaNacimiento() {
		if (tfFechaNacimiento == null) {
			tfFechaNacimiento = new JDateChooser();
			tfFechaNacimiento.setBounds(100, 53, 95, 20);
		}
		return tfFechaNacimiento;
	}
}
