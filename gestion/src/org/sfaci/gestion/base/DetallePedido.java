package org.sfaci.gestion.base;

import javax.persistence.*;

/**
 * Detalles de Pedido
 */
@Entity
@Table(name="detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="precio")
    private float precio;
    @Column(name="unidades")
    private int unidades;

    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name="id_producto")
    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
