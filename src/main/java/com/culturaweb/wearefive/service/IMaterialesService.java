package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.MaterialRequestDTO;
import com.culturaweb.wearefive.dto.MaterialesDTO;

import java.util.List;

public interface IMaterialesService {
    public String agregarMaterial(MaterialRequestDTO payload);
    public String editarMaterial(int id, MaterialRequestDTO payload);
    public String eliminarMaterial(int id);
    public MaterialesDTO listarMateriales();
}
