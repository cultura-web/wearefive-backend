package com.culturaweb.wearefive.dto;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetalleModeloZapatoDTO {
    private String nombre;
    private int precioVenta;
    private String imagenUrl;
    private String descripcion;
    private String color;
    private String tipo;
    private String material;
}
