package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEnProcesoDTO {

    @PositiveOrZero(message = "El id del material no puede ser negativo")
    private Integer idMaterial;

    @PositiveOrZero(message = "La cantidad de material no puede ser negativa")
    private Integer cantidad;
}
