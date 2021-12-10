package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostosMaterialDTO {
    private int idMaterial;
    private String nombre;
    private String detalle;
    private String unidad;
    private int precioUnitario;
    private int cantidad;
    private int costoTotal;
}
