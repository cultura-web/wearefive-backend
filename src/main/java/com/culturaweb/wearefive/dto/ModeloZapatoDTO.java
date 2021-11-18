package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class ModeloZapatoDTO {

    private String nombre;
    private String descripcion;
    private String color;
    private String tipo;
    private int precioUnitario;
    private int costoTotal;
    private String material;
    private String detalle;

}
