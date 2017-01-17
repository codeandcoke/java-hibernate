package org.sfsoft.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Clase que mapea un objeto Personaje con la tabla de MySQL correspondiente, utilizando anotaciones
 * @author Santiago Faci
 * @version 3.0
 */
@Entity
@Table(name="Personaje")
public class Personaje implements Serializable {

	@Id					// Marca el campo como la clave de la tabla
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")  // Opcional si coinciden atributo y columna. 
						// Indica la columna de la tabla que corresponde con este atributo
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="vida")
	private int vida;
	
	@Column(name="puntos")
	private int puntos;
	
	// Anota una relaci—n N-M entre dos tablas e indica el atributo (de la otra clase)
	// que ha definido los par‡metros de la relaci—n
	// Se utiliza un interfaz List para la colecci—n pero ser’a m‡s adecuado utilizar el interfaz Set
	// puesto que los datos no est‡n ordenados
	@ManyToMany(cascade = CascadeType.DETACH)		// Cuando se elimine un personaje se desv’ncular‡ el arma pero Žsta no se borrar‡
	@JoinTable(name="personaje_arma", 
		joinColumns={@JoinColumn(name="id_personaje")}, inverseJoinColumns={@JoinColumn(name="id_arma")})
	private List<Arma> armas;
	
	// Constructor vac’o. Hibernate puede mostrar algœn error si no est‡ implementado
	public Personaje() {
		
		// En caso de hacerse con un Set, aqui se implementar’a la clase HashSet
		this.armas = new ArrayList<Arma>();
	}
	
	public Personaje(int id, String nombre, int vida, int puntos) {
		this.id = id;
		this.nombre = nombre;
		this.vida = vida;
		this.puntos = puntos;
		this.armas = new ArrayList<Arma>();
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

	public List<Arma> getArmas() {
		return armas;
	}

	public void setArmas(List<Arma> armas) {
		this.armas = armas;
	}
	
	public String getNombreArmas() {
		
		String nombreArmas = "";
		
		for (Arma arma : armas) {
			nombreArmas += arma.getNombre() + ", ";
		}
		
		return nombreArmas;
	}
	
	
}
