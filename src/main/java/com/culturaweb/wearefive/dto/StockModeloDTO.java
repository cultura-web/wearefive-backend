package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
public class StockModeloDTO {

    @Positive(message = "El id del modelo no puede ser negativo o cero")
    private int idModelo;


    private String nombreModelo;

    @NotNull(message = "La Lista no puede quedar vacía")
    private List<@Valid EjemplarDTO> ejemplares;
}