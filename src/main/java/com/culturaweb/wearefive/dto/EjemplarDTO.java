package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
public class EjemplarDTO {
    @PositiveOrZero(message = "la cantidad no puede quedar negativa")
    private int cantidad;

    @NotEmpty(message = "La talla no puede quedar vacía")
    private String talla;
}
