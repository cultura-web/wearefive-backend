package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoRecibidoDTO;
import com.culturaweb.wearefive.dto.ModelosDTO;

public interface IModeloZapatoService {
    public void agregarModelo(ModeloZapatoRecibidoDTO modelo);

    public void editarModelo(int id, ModeloZapatoRecibidoDTO modelo);

    public void eliminarModelo(int id);

    public ModelosDTO listarModelos();

    public DetalleModeloZapatoDTO getDetalleModeloZapato(int id);

    public ModelosDTO buscarZapatosPorNombre(String nombre);
}
