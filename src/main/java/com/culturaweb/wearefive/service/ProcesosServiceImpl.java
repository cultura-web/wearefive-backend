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

    public ProcesosServiceImpl(IProcesoRepository procesoRepository, IModeloZapatoRepository modeloZapatoRepository, IMaterialRepository materialRepository) {
        this.procesoRepository = procesoRepository;
        this.modeloZapatoRepository = modeloZapatoRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public String agregarProcesoAMaterial(CreacionProcesoDTO procesoDTO, int idModelo) {
        Optional<ModeloZapato> optional = this.modeloZapatoRepository.findById(idModelo);
        if(optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        ModeloZapato m = optional.get();

        Proceso p = new Proceso();//TODO
        List<Material> materiales = getMateriales(procesoDTO.getMateriales());

        Iterator<MaterialEnProcesoDTO> iterator = procesoDTO.getMateriales().iterator();
        for(Material material:materiales){
            MaterialDeProceso materialDeProceso = new MaterialDeProceso(iterator.next().getCantidad(),material,p);
            p.getMaterialDeProcesos().add(materialDeProceso);
        }
        this.procesoRepository.save(p);
        return null;
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
}
