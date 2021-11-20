package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.ModeloZapatoDTO;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeloZapatoServicelpml implements  IModeloZapatoService{
    @Autowired
    IModeloZapatoRepository imodeloRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void agregarModelo(ModeloZapatoDTO modelo) {
        ModeloZapato mo = this.modelMapper.map(modelo, ModeloZapato.class);
        this.imodeloRepository.save(mo);
    }

    @Override
    public void editarModelo(int Id, ModeloZapatoDTO modelo) {
        ModeloZapato mo = this.imodeloRepository.getById(Id);

        mo.setNombre(modelo.getNombre());
        mo.setDescripcion(modelo.getDescripcion());
        mo.setTipo(modelo.getTipo());
        mo.setMaterial(modelo.getMaterial());
        mo.setColor(modelo.getColor());
        mo.setCosto(modelo.getCostoTotal());
        mo.setPreciounitario(modelo.getPrecioUnitario());
        mo.setDetalle(modelo.getDetalle());
        mo.setDescuento(modelo.getDescuento());
        this.imodeloRepository.save(mo);
    }


    @Override
    public void eliminarModelo(int id) {
        Optional<ModeloZapato> optional = this.imodeloRepository.findById(id);
        ModeloZapato mo = optional.get();
        this.imodeloRepository.delete(mo);
    }
}
