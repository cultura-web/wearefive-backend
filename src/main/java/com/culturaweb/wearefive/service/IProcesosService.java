package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.CreacionProcesoDTO;

public interface IProcesosService {
    public String agregarProcesoAMaterial(CreacionProcesoDTO procesoDTO, int idModelo);
}
