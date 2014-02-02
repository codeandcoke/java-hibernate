package org.sfsoft.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

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
 * Clase que representa las armas de los Personajes
 * @author Santiago Faci
 * @version 3.0
 *
 */
@Entity
@Table(name="armas")
public class Arma {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="dano")
	private int dano;
	
	// Anota una relación N-M entre dos tablas e indica cual es la tabla que mantiene la relación
	// y los campos que relacionan las dos tablas
	// Se utiliza un interfaz List para la colección pero sería más adecuado utilizar el interfaz Set
	// puesto que los datos no están ordenados
	@ManyToMany(cascade = CascadeType.DETACH, mappedBy = "armas")
	// Cuando se elimine un arma se desvinculará de su personaje pero éste no se eliminará
	private List<Enemigo> enemigos;
	
	public Arma() {
		
		// En caso de utilizar la interfaz Set, aqu� se implementar�a la clase HashSet
		this.enemigos = new ArrayList<Enemigo>();
	}
	
	public Arma(int id, String nombre, String descripcion, int dano) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dano = dano;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}
	
	public List<Enemigo> getEnemigos() {
		return enemigos;
	}
	
	public void setEnemigos(List<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}
	
	
}
