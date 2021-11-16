package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "modelo_zapato")
@Entity
public class ModeloZapato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "color", length = 45)
    private String color;

    @Column(name = "costo")
    private Integer costo;

    @Column(name = "preciounitario")
    private Integer preciounitario;

    @Column(name = "descuento")
    private Integer descuento;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "detalle", length = 250)
    private String detalle;

    @Column(name = "material", length = 45)
    private String material;

    @Column(name = "imagenurl", length = 200)
    private String imagenurl;

    @Column(name = "tipo", length = 45)
    private String tipo;
}