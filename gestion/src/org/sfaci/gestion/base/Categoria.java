package org.sfaci.gestion.base;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Categorias de productos
 */
@Entity
@Table(name="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name="producto_categoria",
        joinColumns={@JoinColumn(name="id_categoria")},
        inverseJoinColumns={@JoinColumn(name="id_producto")})
    List<Producto> productos;

    public Categoria() {
        productos = new ArrayList<Producto>();
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

    public List<Producto> getProductos() {
        return productos;
    }
}
