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

    @ManyToOne
    @JoinColumn(name = "modelo_zapato_id")
    private ModeloZapato modeloZapato;

    @OneToMany(mappedBy = "proceso")
    private List<MaterialDeProceso> materialDeProcesos;

}
