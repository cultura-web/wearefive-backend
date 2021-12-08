package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CostosProcesoDTO {
    private int idProceso;
    private String nombre;
    private String detalle;
    private int costoTotal;
    private List<CostosMaterialDTO> materiales;

    public CostosProcesoDTO(int idProceso, String nombre, String detalle, int costoTotal) {
        this.idProceso = idProceso;
        this.nombre = nombre;
        this.detalle = detalle;
        this.costoTotal = costoTotal;
    }
}