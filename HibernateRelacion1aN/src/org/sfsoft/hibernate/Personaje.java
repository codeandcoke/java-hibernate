package org.sfsoft.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que mapea un objeto Personaje con la tabla de MySQL correspondiente, utilizando anotaciones
 * @author Santiago Faci
 * @version curso 2014-2015
 */
@Entity
@Table(name="personajes")
public class Personaje implements Serializable {

	@Id					// Marca el campo como la clave de la tabla
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")  // Opcional si coinciden atributo y columna. 
						// Indica la columna de la tabla que corresponde con este atributo
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="nivel")
	private int nivel;
	
	@Column(name="energia")
	private int energia;
	
	@Column(name="puntos")
	private int puntos;
	
	@ManyToOne
	@JoinColumn(name="id_arma")
	private Arma arma;
	
	// Constructor vacío. Hibernate puede mostrar algún error si no está implementado
	public Personaje() {}
	
	public Personaje(int id, String nombre, int nivel, int energia, int puntos, Arma arma) {
		this.id = id;
		this.nombre = nombre;
		this.nivel = nivel;
		this.energia = energia;
		this.puntos = puntos;
		this.arma = arma;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public int getEnergia() {
		return energia;
	}
	
	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}
	
	
}
