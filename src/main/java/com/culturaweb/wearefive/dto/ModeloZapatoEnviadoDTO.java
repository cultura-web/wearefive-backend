package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ModeloZapatoEnviadoDTO {

    private String nombre;
    private Integer precioVenta;
    private String imagenUrl;
}
