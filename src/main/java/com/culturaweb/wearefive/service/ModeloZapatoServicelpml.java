package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoEnviadoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoRequestDTO;
import com.culturaweb.wearefive.dto.ModelosDTO;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.NombreDeModeloDeZapatoYaExisteException;
import com.culturaweb.wearefive.exceptions.PrecioUnitarioException;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloZapatoServicelpml implements  IModeloZapatoService{
    @Autowired
    IModeloZapatoRepository imodeloRepository;
    @Autowired
    ModelMapper modelMapper;

    public ModeloZapatoServicelpml(IModeloZapatoRepository imodeloRepository, ModelMapper modelMapper) {
        this.imodeloRepository = imodeloRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void agregarModelo(ModeloZapatoRequestDTO modelo) {
        if(this.imodeloRepository.existsByNombreEquals(modelo.getNombre()))
            throw new NombreDeModeloDeZapatoYaExisteException();
        if(modelo.getCostoTotal() > modelo.getPrecioUnitario())
            throw new PrecioUnitarioException();
        ModeloZapato mo = this.modelMapper.map(modelo, ModeloZapato.class);
        this.imodeloRepository.save(mo);
    }

    @Override
    public void editarModelo(int id, ModeloZapatoRequestDTO modelo) {

        if(!this.imodeloRepository.existsById(id))
            throw new ModeloDeZapatoNoExisteException();

        if(modelo.getCostoTotal() > modelo.getPrecioUnitario())
            throw new PrecioUnitarioException();

        ModeloZapato mo = this.imodeloRepository.getById(id);
        if(!modelo.getNombre().equals(mo.getNombre()))
        {
            if(this.imodeloRepository.existsByNombreEquals(modelo.getNombre()))
                throw new NombreDeModeloDeZapatoYaExisteException();
        }
        mo = this.modelMapper.map(modelo,ModeloZapato.class);
        mo.setId(id);
        this.imodeloRepository.save(mo);
    }

    @Override
    public void eliminarModelo(int id) {
        if(!this.imodeloRepository.existsById(id))
            throw new ModeloDeZapatoNoExisteException();
        this.imodeloRepository.deleteById(id);
    }

    @Override
    public ModelosDTO listarModelos() {
        List<ModeloZapato> zapatoList = this.imodeloRepository.findAll();
        List<ModeloZapatoEnviadoDTO> zapatosEnviados = new ArrayList<>();
        for (ModeloZapato m:zapatoList)
            zapatosEnviados.add(new ModeloZapatoEnviadoDTO(m.getId(),m.getNombre(),(m.getPrecioUnitario()*(100-m.getDescuento()))/100,m.getImagenurl()));
        return new ModelosDTO(zapatosEnviados);
    }

    @Override
    public DetalleModeloZapatoDTO getDetalleModeloZapato(int id) {
        Optional<ModeloZapato> optional = this.imodeloRepository.findById(id);
        if(optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        ModeloZapato modeloZapato = optional.get();
        DetalleModeloZapatoDTO detalleModeloZapatoDTO = this.modelMapper.map(modeloZapato,DetalleModeloZapatoDTO.class);
        detalleModeloZapatoDTO.setPrecioVenta((modeloZapato.getPrecioUnitario()*(100-modeloZapato.getDescuento()))/100);
        return detalleModeloZapatoDTO;
    }

    @Override
    public ModelosDTO buscarZapatosPorNombre(String nombre) {
        List<ModeloZapato> zapatoList = this.imodeloRepository.findByNombreContains(nombre);
        List<ModeloZapatoEnviadoDTO> zapatosEnviados = new ArrayList<>();
        for (ModeloZapato m:zapatoList)
            zapatosEnviados.add(new ModeloZapatoEnviadoDTO(m.getId(),m.getNombre(),(m.getPrecioUnitario()*(100-m.getDescuento()))/100,m.getImagenurl()));
        return new ModelosDTO(zapatosEnviados);
    }
}
