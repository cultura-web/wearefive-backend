package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.CreacionProcesoDTO;
import com.culturaweb.wearefive.dto.MaterialEnProcesoDTO;
import com.culturaweb.wearefive.exceptions.MaterialNoEncontradoException;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.ProcesoDeUnModeloYaExisteException;
import com.culturaweb.wearefive.exceptions.ProcesoNoExisteException;
import com.culturaweb.wearefive.model.Material;
import com.culturaweb.wearefive.model.MaterialDeProceso;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.model.Proceso;
import com.culturaweb.wearefive.repository.IMaterialDeProcesoRepository;
import com.culturaweb.wearefive.repository.IMaterialRepository;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.repository.IProcesoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProcesosServiceImpl implements IProcesosService {

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
        if (optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        if (this.procesoRepository.existsByNombreEqualsAndModeloZapato_IdEquals(procesoDTO.getNombre(), idModelo))
            throw new ProcesoDeUnModeloYaExisteException();

        ModeloZapato m = optional.get();
        List<Material> materiales = getMateriales(procesoDTO.getMateriales());
        Proceso p = mapearProceso(m, procesoDTO.getNombre(), procesoDTO.getDetalle(), materiales, procesoDTO.getMateriales());

        Iterator<MaterialEnProcesoDTO> iterator = procesoDTO.getMateriales().iterator();
        for (Material material : materiales) {
            MaterialDeProceso materialDeProceso = new MaterialDeProceso(iterator.next().getCantidad(), material, p);
            this.materialDeProcesoRepository.save(materialDeProceso);
        }
        return "OK";
    }

    @Override
    @Transactional
    public String editarProcesoAMaterial(CreacionProcesoDTO procesoDTO, int idProceso) {
        Proceso p = getProceso(idProceso);
        if(!p.getNombre().equals(procesoDTO.getNombre())){
            if(this.procesoRepository.existsByNombreEqualsAndModeloZapato_IdEquals(procesoDTO.getNombre(),p.getModeloZapato().getId()))
                throw new ProcesoDeUnModeloYaExisteException();
        }
        List<Material> nuevosMateriales = getMateriales(procesoDTO.getMateriales());

        p.setNombre(procesoDTO.getNombre());
        p.setDetalle(procesoDTO.getDetalle());
        p.setCostoTotal(calcularCostoTotal(nuevosMateriales,procesoDTO.getMateriales()));
        Iterator<MaterialEnProcesoDTO> iterator = procesoDTO.getMateriales().iterator();
        Iterator<Material> iteratorMaterial = nuevosMateriales.iterator();

        List<MaterialDeProceso> materialesABorrar = new ArrayList<>();
        for(MaterialDeProceso mp:p.getMaterialDeProcesos())
        {
            if(iterator.hasNext() && iteratorMaterial.hasNext()){
                mp.setCantidad(iterator.next().getCantidad());
                mp.setMaterial(iteratorMaterial.next());
            }else{
                    materialesABorrar.add(mp);
            }
        }
        while(iterator.hasNext() && iteratorMaterial.hasNext())
            p.getMaterialDeProcesos().add(new MaterialDeProceso(iterator.next().getCantidad(),iteratorMaterial.next(),p));

        for (MaterialDeProceso mp:materialesABorrar){
            p.getMaterialDeProcesos().remove(mp);
            this.materialDeProcesoRepository.deleteById(mp.getId());
        }

        this.procesoRepository.save(p);
        return "OK";
    }

    @Override
    @Transactional()
    public String eliminarProcesoAMaterial(int idProceso) {
        if (!this.procesoRepository.existsById(idProceso))
            throw new ProcesoNoExisteException();
        this.materialDeProcesoRepository.removeByProceso_IdEquals(idProceso);
        return "OK";
    }

    private Proceso getProceso(int idProceso) {
        Optional<Proceso> optional = this.procesoRepository.findById(idProceso);
        if (optional.isEmpty())
            throw new ProcesoNoExisteException();
        return optional.get();
    }

    private List<Material> getMateriales(List<MaterialEnProcesoDTO> mat) {
        List<Material> materiales = new ArrayList<>();
        for (MaterialEnProcesoDTO m : mat) {
            Optional<Material> optionalMaterial = this.materialRepository.findById(m.getIdMaterial());
            if (optionalMaterial.isEmpty())
                throw new MaterialNoEncontradoException();
            materiales.add(optionalMaterial.get());
        }
        return materiales;
    }

    private Proceso mapearProceso(ModeloZapato m, String nombre, String detalle, List<Material> materiales, List<MaterialEnProcesoDTO> materialEnProcesoDTO) {
        int costoTotal = calcularCostoTotal(materiales, materialEnProcesoDTO);
        return new Proceso(nombre, detalle, costoTotal, m);
    }

    private int calcularCostoTotal(List<Material> materiales, List<MaterialEnProcesoDTO> materialEnProcesoDTOS) {
        Iterator<MaterialEnProcesoDTO> iterator = materialEnProcesoDTOS.iterator();
        int r = 0;
        for (Material m : materiales) {
            r += m.getPrecioUnitario() * iterator.next().getCantidad();
        }
        return r;
    }
}
