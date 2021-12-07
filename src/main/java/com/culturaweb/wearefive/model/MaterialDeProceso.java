package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "material_de_proceso")
@Entity
public class MaterialDeProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    public MaterialDeProceso(int cantidad, Material material, Proceso proceso) {
        this.cantidad = cantidad;
        this.material = material;
        this.proceso = proceso;
    }
}
