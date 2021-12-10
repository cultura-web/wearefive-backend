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
@Table(name = "proceso")
@Entity
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "costo_total")
    private int costoTotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "modelo_zapato_id")
    private ModeloZapato modeloZapato;

    @OneToMany(mappedBy = "proceso", cascade = CascadeType.ALL)
    private List<MaterialDeProceso> materialDeProcesos;

    public Proceso(String nombre, String detalle, int costoTotal, ModeloZapato modeloZapato) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.costoTotal = costoTotal;
        this.modeloZapato = modeloZapato;
    }
}
