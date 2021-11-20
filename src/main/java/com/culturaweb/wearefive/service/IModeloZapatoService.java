package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.ModeloZapatoDTO;

public interface IModeloZapatoService {
    public void agregarModelo(ModeloZapatoDTO modelo);

    public void editarModelo(int id, ModeloZapatoDTO modelo);

    public void eliminarModelo(int id);
}
