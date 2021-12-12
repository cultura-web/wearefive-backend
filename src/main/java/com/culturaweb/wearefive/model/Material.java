package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "material")
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "precio_unitario")
    private int precioUnitario;

    @Column(name = "unidad")
    private String unidad;

    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE)
    private List<MaterialDeProceso> materialDeProcesos;

    public Material(String nombre, String detalle, int precioUnitario, String unidad) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.precioUnitario = precioUnitario;
        this.unidad = unidad;
    }
}
