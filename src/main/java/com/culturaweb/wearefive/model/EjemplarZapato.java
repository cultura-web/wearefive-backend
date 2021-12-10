package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dom4j.rule.Mode;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "ejemplar_zapato")
@Entity
public class EjemplarZapato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "talla")
    private String talla;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "modelo_zapato_id")
    private ModeloZapato modeloZapato;

    public EjemplarZapato(String talla, ModeloZapato modeloZapato) {
        this.talla = talla;
        this.modeloZapato = modeloZapato;
    }
}
