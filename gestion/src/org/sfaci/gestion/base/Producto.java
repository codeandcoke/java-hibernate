package org.sfaci.gestion.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Productos
 */
@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private float precio;

    @OneToMany(mappedBy="producto")
    private List<DetallePedido> detallesPedido;
    @ManyToMany(mappedBy="productos")
    private List<Categoria> categorias;

    public Producto() {
        detallesPedido = new ArrayList<DetallePedido>();
        categorias = new ArrayList<Categoria>();
    }

    public int getId() {
        return id;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
