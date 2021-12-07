package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialResponseDTO {
    private Integer idMaterial;
    private String nombre;
    private Integer precioUnitario;
}
