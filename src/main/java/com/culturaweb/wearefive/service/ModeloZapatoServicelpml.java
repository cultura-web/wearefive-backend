package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.*;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.NombreDeModeloDeZapatoYaExisteException;
import com.culturaweb.wearefive.exceptions.PrecioUnitarioException;
import com.culturaweb.wearefive.model.MaterialDeProceso;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.model.Proceso;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.repository.IProcesoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloZapatoServicelpml implements  IModeloZapatoService{

    IModeloZapatoRepository modeloRepository;
    IProcesoRepository procesoRepository;
    ModelMapper modelMapper;

    public ModeloZapatoServicelpml(IModeloZapatoRepository modeloRepository, IProcesoRepository procesoRepository, ModelMapper modelMapper) {
        this.modeloRepository = modeloRepository;
        this.procesoRepository = procesoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void agregarModelo(ModeloZapatoRequestDTO modelo) {
        if(this.modeloRepository.existsByNombreEquals(modelo.getNombre()))
            throw new NombreDeModeloDeZapatoYaExisteException();
        if(modelo.getCostoTotal() > modelo.getPrecioUnitario())
            throw new PrecioUnitarioException();
        ModeloZapato mo = this.modelMapper.map(modelo, ModeloZapato.class);
        this.modeloRepository.save(mo);
    }

    @Override
    public void editarModelo(int id, ModeloZapatoRequestDTO modelo) {

        if(modelo.getCostoTotal() > modelo.getPrecioUnitario())
            throw new PrecioUnitarioException();

        ModeloZapato mo = getModeloZapato(id);
        if(!modelo.getNombre().equals(mo.getNombre()))
        {
            if(this.modeloRepository.existsByNombreEquals(modelo.getNombre()))
                throw new NombreDeModeloDeZapatoYaExisteException();
        }
        mo = this.modelMapper.map(modelo,ModeloZapato.class);
        mo.setId(id);
        this.modeloRepository.save(mo);
    }

    @Override
    public void eliminarModelo(int id) {
        if(!this.modeloRepository.existsById(id))
            throw new ModeloDeZapatoNoExisteException();
        this.modeloRepository.deleteById(id);
    }

    @Override
    public ModelosDTO listarModelos() {
        List<ModeloZapato> zapatoList = this.modeloRepository.findAll();
        List<ModeloZapatoEnviadoDTO> zapatosEnviados = new ArrayList<>();
        for (ModeloZapato m:zapatoList)
            zapatosEnviados.add(new ModeloZapatoEnviadoDTO(m.getId(),m.getNombre(),(m.getPrecioUnitario()*(100-m.getDescuento()))/100,m.getImagenurl()));
        return new ModelosDTO(zapatosEnviados);
    }

    @Override
    public DetalleModeloZapatoDTO getDetalleModeloZapato(int id) {
        ModeloZapato modeloZapato = getModeloZapato(id);
        DetalleModeloZapatoDTO detalleModeloZapatoDTO = this.modelMapper.map(modeloZapato,DetalleModeloZapatoDTO.class);
        detalleModeloZapatoDTO.setPrecioVenta((modeloZapato.getPrecioUnitario()*(100-modeloZapato.getDescuento()))/100);
        return detalleModeloZapatoDTO;
    }

    @Override
    public ModelosDTO buscarZapatosPorNombre(String nombre) {
        List<ModeloZapato> zapatoList = this.modeloRepository.findByNombreContains(nombre);
        List<ModeloZapatoEnviadoDTO> zapatosEnviados = new ArrayList<>();
        for (ModeloZapato m:zapatoList)
            zapatosEnviados.add(new ModeloZapatoEnviadoDTO(m.getId(),m.getNombre(),(m.getPrecioUnitario()*(100-m.getDescuento()))/100,m.getImagenurl()));
        return new ModelosDTO(zapatosEnviados);
    }

    @Override
    public CostosModeloDTO getCostosModelo(int id) {
        ModeloZapato m = getModeloZapato(id);
        List<Proceso> procesos = this.procesoRepository.findByModeloZapato_IdEquals(id);

        CostosModeloDTO r = new CostosModeloDTO(m.getId(),m.getNombre(),new ArrayList<>());
        int costoTotalModelo = 0;
        for (Proceso p:procesos){
            costoTotalModelo += p.getCostoTotal();
            CostosProcesoDTO costosProcesoDTO = new CostosProcesoDTO(p.getId(),p.getNombre(),p.getDetalle(),p.getCostoTotal(),new ArrayList<>());
            for(MaterialDeProceso mp:p.getMaterialDeProcesos()){
                CostosMaterialDTO costosMaterialDTO = new CostosMaterialDTO(
                        mp.getMaterial().getId(),
                        mp.getMaterial().getNombre(),
                        mp.getMaterial().getDetalle(),
                        mp.getMaterial().getUnidad(),
                        mp.getMaterial().getPrecioUnitario(),
                        mp.getCantidad(),
                        mp.getMaterial().getPrecioUnitario()*mp.getCantidad()
                );
                costosProcesoDTO.getMateriales().add(costosMaterialDTO);
            }
            r.getProcesos().add(costosProcesoDTO);
        }
        r.setCostoTotal(costoTotalModelo);
        return r;
    }

    private ModeloZapato getModeloZapato(int id){
        Optional<ModeloZapato> optional = this.modeloRepository.findById(id);
        if(optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        return optional.get();
    }
}
