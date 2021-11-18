package com.culturaweb.wearefive.model.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "usuario", indexes = {
        @Index(name = "correo_UNIQUE", columnList = "correo", unique = true),
        @Index(name = "username_UNIQUE", columnList = "username", unique = true)
})
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombres", length = 45)
    private String nombres;

    @Column(name = "apellidos", length = 45)
    private String apellidos;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "`contrasena`", length = 45)
    private String password;

    @Column(name = "correo", length = 45)
    private String correo;


}
