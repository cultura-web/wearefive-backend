package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.*;

public interface IModeloZapatoService {
    public void agregarModelo(ModeloZapatoRequestDTO modelo);

    public void editarModelo(int id, ModeloZapatoRequestDTO modelo);

    public void eliminarModelo(int id);

    public ModelosDTO listarModelos();

    public DetalleModeloZapatoDTO getDetalleModeloZapato(int id);

    public ModelosDTO buscarZapatosPorNombre(String nombre);

    public CostosModeloDTO getCostosModelo(int id);

    public StockModeloDTO consultarStock(int id);

    public String actualizarStock(StockModeloDTO payload);
}
