package com.culturaweb.wearefive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "`q&a`")
@Entity
public class QA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pregunta", length = 250)
    private String pregunta;

    @Column(name = "respuesta", length = 250)
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Admin admin;
}