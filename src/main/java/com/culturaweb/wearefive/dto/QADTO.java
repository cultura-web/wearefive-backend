package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QADTO {
    @NotEmpty(message = "la pregunta no puede quedar vacía")
    @Size(max = 250, message = "no se puede ingresar una pregunta con más de 250 caracteres")
    private String pregunta;

    @NotEmpty(message = "la respuesta no puede quedar vacía")
    @Size(max = 250, message = "no se puede ingresar una respuesta con más de 250 caracteres")
    private String respuesta;
}
