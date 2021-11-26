package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "qa")
@Entity
public class QA {

    public QA(String pregunta, String respuesta, Admin admin) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.admin = admin;
    }

    public QA(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pregunta", length = 250)
    private String pregunta;

    @Column(name = "respuesta", length = 250)
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
