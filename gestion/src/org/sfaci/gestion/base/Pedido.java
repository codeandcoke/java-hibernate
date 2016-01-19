package org.sfaci.gestion.base;

import javax.persistence.*;
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
}
