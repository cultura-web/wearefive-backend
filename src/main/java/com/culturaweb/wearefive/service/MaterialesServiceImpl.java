package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.MaterialRequestDTO;
import com.culturaweb.wearefive.dto.MaterialResponseDTO;
import com.culturaweb.wearefive.dto.MaterialesDTO;
import com.culturaweb.wearefive.exceptions.MaterialNoEncontradoException;
import com.culturaweb.wearefive.model.Material;
import com.culturaweb.wearefive.repository.IMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialesServiceImpl implements IMaterialesService{

    private final IMaterialRepository materialRepository;

    public MaterialesServiceImpl(IMaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public String agregarMaterial(MaterialRequestDTO payload) {
        Material m = new Material(payload.getNombre(),payload.getDetalle(),payload.getPrecioUnitario(),payload.getUnidad());
        this.materialRepository.save(m);
        return "OK";
    }

    @Override
    public String editarMaterial(int id, MaterialRequestDTO payload) {
        Optional<Material> optional = this.materialRepository.findById(id);
        if(optional.isEmpty())
            throw new MaterialNoEncontradoException();
        Material m = optional.get();
        m.setNombre(payload.getNombre());
        m.setDetalle(payload.getDetalle());
        m.setPrecioUnitario(payload.getPrecioUnitario());
        m.setUnidad(payload.getUnidad());
        this.materialRepository.save(m);
        return "OK";
    }

    @Override
    public String eliminarMaterial(int id) {
        if(!this.materialRepository.existsById(id))
            throw new MaterialNoEncontradoException();
        this.materialRepository.deleteById(id);
        return "OK";
    }

    @Override
    public MaterialesDTO listarMateriales() {
        List<Material> modelos = this.materialRepository.findAll();
        List<MaterialResponseDTO> materiales = new ArrayList<>();
        for(Material m:modelos)
            materiales.add(new MaterialResponseDTO(m.getId(),m.getNombre(),m.getPrecioUnitario()));
        return new MaterialesDTO(materiales);
    }
}
