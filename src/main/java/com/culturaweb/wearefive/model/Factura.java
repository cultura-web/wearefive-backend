package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "factura")
@Entity
public class Factura {

    @Id
    private String id;

    @Column(name = "total")
    private Integer total;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "status")
    private String status;

    @Column(name = "id_ejemplar")
    private Integer idEjemplar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "modelo_zapato_id")
    private ModeloZapato modeloZapato;

    public Factura(String id, int total, LocalDate fechaCompra, String status, int idEjemplar,Cliente cliente, ModeloZapato modeloZapato) {
        this.id = id;
        this.total = total;
        this.fechaCompra = fechaCompra;
        this.status = status;
        this.idEjemplar = idEjemplar;
        this.cliente = cliente;
        this.modeloZapato = modeloZapato;
    }

    @Override
    public String toString() {
        return "Factura{" + "\n" +
                "   id='" + id + "\n" +
                "   total=" + total + "\n" +
                "   fechaCompra=" + fechaCompra + "\n" +
                "   status='" + status + "\n" +
                "   idEjemplar=" + idEjemplar + "\n" +
                "   cliente=" + cliente.toString() + "\n" +
                "   modeloZapato=" + modeloZapato.getNombre() + "\n" +
                "   idModeloZapato=" + modeloZapato.getId() + "\n" +
                '}' + "\n";
    }
}
