package com.culturaweb.wearefive.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModeloZapatoEnviadoDTO {

    private Integer id;
    private String nombre;
    private Integer precioVenta;
    private String imagenUrl;
}
