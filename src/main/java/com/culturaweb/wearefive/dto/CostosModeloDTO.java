package com.culturaweb.wearefive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CostosModeloDTO {
    private int idModelo;
    private String nombreModelo;
    private int costoTotal;
    private List<CostosProcesoDTO> procesos;

    public CostosModeloDTO(int idModelo, String nombreModelo,List<CostosProcesoDTO> procesos) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.procesos = procesos;
    }
}
