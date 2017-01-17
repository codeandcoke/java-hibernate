package org.sfsoft.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que mapea un objeto Personaje con la tabla de MySQL correspondiente, utilizando anotaciones
 * @author Santiago Faci
 *
 */
@Entity
@Table(name="Personaje")
public class Personaje implements Serializable {

	@Id					// Marca el campo como la clave de la tabla
	@Column(name="id")  // Opcional si coinciden atributo y columna. 
						// Indica la columna de la tabla que corresponde con este atributo
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="vida")
	private int vida;
	
	@Column(name="puntos")
	private int puntos;
	
	@Column(name="arma")
	private String arma;
	
	// Constructor vac’o. Hibernate puede mostrar algœn error si no est‡ implementado
	public Personaje() {}
	
	public Personaje(int id, String nombre, int vida, int puntos, String arma) {
		this.id = id;
		this.nombre = nombre;
		this.vida = vida;
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

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getArma() {
		return arma;
	}

	public void setArma(String arma) {
		this.arma = arma;
	}
	
	
}
