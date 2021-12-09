package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.CreacionProcesoDTO;

public interface IProcesosService {
    public String agregarProcesoAMaterial(CreacionProcesoDTO procesoDTO, int idModelo);
    public String editarProcesoAMaterial(CreacionProcesoDTO procesoDTO, int idProceso);
    public String eliminarProcesoAMaterial(int idProceso);
}
