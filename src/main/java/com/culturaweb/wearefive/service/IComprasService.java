package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.DetalleCompraDTO;

public interface IComprasService {
    public DetalleCompraDTO comprarUnEjemplar(int idModelo, String talla, String token);
}
