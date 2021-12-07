package com.culturaweb.wearefive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
public class MaterialRequestDTO {
    @NotEmpty(message ="El nombre del material no puede ser vacío")
    private String nombre;

    @NotEmpty(message = "El detalle del material no puede ser vacío")
    private String detalle;

    @PositiveOrZero(message = "El precio unitario del material no puede ser negativo")
    private int precioUnitario;

    @NotEmpty(message = "La unidad del material no puede ser vacía")
    private String unidad;
}
