package org.sfaci.gestion.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Pedidos
 */
@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="numero")
    private String numero;
    @Column(name="fecha")
    private Date fecha;
    @Column(name="fecha_entrega")
    private Date fechaEntrega;
    @Column(name="base_imponible")
    private float baseImponible;
    @Column(name="iva")
    private float iva;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @OneToMany(mappedBy="pedido")
    private List<DetallePedido> detallesPedido;

    public Pedido() {
        detallesPedido = new ArrayList<DetallePedido>();
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(float baseImponible) {
        this.baseImponible = baseImponible;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return numero;
    }
}
