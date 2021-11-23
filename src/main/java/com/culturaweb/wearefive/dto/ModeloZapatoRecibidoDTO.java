package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class ModeloZapatoRecibidoDTO {

    @NotEmpty(message = "El nombre del zapato no puede estar vacío")
    @Size(max = 45, message = "El nombre no debe superar los 45 caracteres")
    private String nombre;

    @NotEmpty(message = "La descripción del zapato no puede estar vacía")
    @Size(max = 250, message = "La descripción no debe superar los 250 caracteres")
    private String descripcion;

    @NotEmpty(message = "se debe especificar un color principal del zapato")
    @Size(max = 45, message = "El color no debe superar los 45 caracteres")
    private String color;

    @NotEmpty(message = "Se debe especificar el tipo de zapato")
    @Size(max = 45, message = "El tipo no debe superar los 45 caracteres")
    private String tipo;

    @NotNull(message = "Se debe especificar el precio unitario del zapato")
    @PositiveOrZero
    private int precioUnitario;

    @NotNull(message = "Se debe especificar el costo total de producción del zapato")
    @PositiveOrZero
    private int costoTotal;

    @NotEmpty(message = "Se debe especificar el material del zapato")
    @Size(max = 45, message = "El material no debe superar los 45 caracteres")
    private String material;

    @Size(max = 250, message = "El detalle no debe superar los 250 caracteres")
    private String detalle;

    @Min(value = 0, message = "el porcentaje mínimo de descuento es 0%")
    @Max(value = 100, message = "el porcentaje máximo de descuento es 100%")
    private int descuento;
}
