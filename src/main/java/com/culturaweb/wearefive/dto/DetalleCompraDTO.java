package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCompraDTO {

    private String nombreModelo;
    private String talla;
    private LocalDate fechaCompra;
    private int total;
    private int descuento;
    private String status;
    private String direccionEnvio;

    @Override
    public String toString() {
        return "nombreModelo='" + nombreModelo + "\n" +
                "talla='" + talla + "\n" +
                "fechaCompra=" + fechaCompra + "\n" +
                "total=" + total + "\n" +
                "descuento=" + descuento + "\n" +
                "status='" + status + "\n" +
                "direccionEnvio='" + direccionEnvio + "\n";
    }
}
