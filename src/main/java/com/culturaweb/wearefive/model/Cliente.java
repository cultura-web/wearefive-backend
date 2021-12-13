package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "cliente")
@Entity
public class Cliente implements Serializable {

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "celular", length = 10)
    private String celular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private CarritoCompras carritoCompras;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Cliente(String direccion, String celular, Usuario usuario) {
        this.direccion = direccion;
        this.celular = celular;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return this.usuario.clienteToString();
    }
}
