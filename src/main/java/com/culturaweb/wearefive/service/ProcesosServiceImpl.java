package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.CreacionProcesoDTO;
import com.culturaweb.wearefive.dto.MaterialEnProcesoDTO;
import com.culturaweb.wearefive.exceptions.MaterialNoEncontradoException;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.model.Material;
import com.culturaweb.wearefive.model.MaterialDeProceso;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.model.Proceso;
import com.culturaweb.wearefive.repository.IMaterialDeProcesoRepository;
import com.culturaweb.wearefive.repository.IMaterialRepository;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.repository.IProcesoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProcesosServiceImpl implements IProcesosService{

    private final IProcesoRepository procesoRepository;
    private final IModeloZapatoRepository modeloZapatoRepository;
    private final IMaterialRepository materialRepository;
    private final IMaterialDeProcesoRepository materialDeProcesoRepository;

    public ProcesosServiceImpl(IProcesoRepository procesoRepository, IModeloZapatoRepository modeloZapatoRepository, IMaterialRepository materialRepository, IMaterialDeProcesoRepository materialDeProcesoRepository) {
        this.procesoRepository = procesoRepository;
        this.modeloZapatoRepository = modeloZapatoRepository;
        this.materialRepository = materialRepository;
        this.materialDeProcesoRepository = materialDeProcesoRepository;
    }

    @Override
    public String agregarProcesoAMaterial(CreacionProcesoDTO procesoDTO, int idModelo) {
        Optional<ModeloZapato> optional = this.modeloZapatoRepository.findById(idModelo);
        if(optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        ModeloZapato m = optional.get();

        List<Material> materiales = getMateriales(procesoDTO.getMateriales());
        Proceso p =mapearProceso(m,procesoDTO.getNombre(),procesoDTO.getDetalle(),materiales,procesoDTO.getMateriales());

        Iterator<MaterialEnProcesoDTO> iterator = procesoDTO.getMateriales().iterator();
        for(Material material:materiales){
            MaterialDeProceso materialDeProceso = new MaterialDeProceso(iterator.next().getCantidad(),material,p);
            this.materialDeProcesoRepository.save(materialDeProceso);
        }
        m.setCosto(m.getCosto()+p.getCostoTotal());
        this.modeloZapatoRepository.save(m);
        return "OK";
    }

    private List<Material> getMateriales(List<MaterialEnProcesoDTO> mat){
        List<Material> materiales = new ArrayList<>();
        for(MaterialEnProcesoDTO m:mat){
            Optional<Material> optionalMaterial = this.materialRepository.findById(m.getIdMaterial());
            if(optionalMaterial.isEmpty())
                throw new MaterialNoEncontradoException();
            materiales.add(optionalMaterial.get());
        }
        return materiales;
    }

    private Proceso mapearProceso(ModeloZapato m, String nombre, String detalle, List<Material> materiales, List<MaterialEnProcesoDTO> materialEnProcesoDTO){
        int costoTotal = calcularCostoTotal(materiales,materialEnProcesoDTO);
        return new Proceso(nombre,detalle,costoTotal,m);
    }

    private int calcularCostoTotal(List<Material> materiales, List<MaterialEnProcesoDTO> materialEnProcesoDTOS){
        Iterator<MaterialEnProcesoDTO> iterator = materialEnProcesoDTOS.iterator();
        int r = 0;
        for(Material m:materiales){
            r += m.getPrecioUnitario()*iterator.next().getCantidad();
        }
        return r;
    }
}
