package org.sfaci.gestion.base;

import javax.persistence.*;
import java.util.List;

/**
 * Clientes
 */
@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellidos")
    private String apellidos;
    @Column(name="email")
    private String email;
    @Column(name="telefono")
    private String telefono;

    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos;
}
