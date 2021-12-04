package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreacionProcesoDTO {

    @NotEmpty(message = "El nombre del proceso no puede quedar vacío")
    private String nombre;

    @NotEmpty(message = "El detalle del proceso no puede quedar vacío")
    private String detalle;

    @NotNull
    List<@Valid MaterialEnProcesoDTO> materiales;
}
