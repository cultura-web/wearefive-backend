package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.CostosModeloDTO;
import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoRequestDTO;
import com.culturaweb.wearefive.dto.ModelosDTO;

public interface IModeloZapatoService {
    public void agregarModelo(ModeloZapatoRequestDTO modelo);

    public void editarModelo(int id, ModeloZapatoRequestDTO modelo);

    public void eliminarModelo(int id);

    public ModelosDTO listarModelos();

    public DetalleModeloZapatoDTO getDetalleModeloZapato(int id);

    public ModelosDTO buscarZapatosPorNombre(String nombre);

    public CostosModeloDTO getCostosModelo(int id);
}
