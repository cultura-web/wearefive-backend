package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.*;
import com.culturaweb.wearefive.exceptions.EjemplaresIncompletosException;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.NombreDeModeloDeZapatoYaExisteException;
import com.culturaweb.wearefive.exceptions.PrecioUnitarioException;
import com.culturaweb.wearefive.model.EjemplarZapato;
import com.culturaweb.wearefive.model.MaterialDeProceso;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.model.Proceso;
import com.culturaweb.wearefive.repository.IEjemplarZapatoRepository;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.repository.IProcesoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloZapatoServiceImpl implements IModeloZapatoService {

    IModeloZapatoRepository modeloRepository;
    IProcesoRepository procesoRepository;
    IEjemplarZapatoRepository ejemplarZapatoRepository;
    ModelMapper modelMapper;

    public ModeloZapatoServiceImpl(IModeloZapatoRepository modeloRepository, IProcesoRepository procesoRepository, IEjemplarZapatoRepository ejemplarZapatoRepository, ModelMapper modelMapper) {
        this.modeloRepository = modeloRepository;
        this.procesoRepository = procesoRepository;
        this.ejemplarZapatoRepository = ejemplarZapatoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void agregarModelo(ModeloZapatoRequestDTO modelo) {
        if (this.modeloRepository.existsByNombreEquals(modelo.getNombre()))
            throw new NombreDeModeloDeZapatoYaExisteException();
        if (modelo.getCostoTotal() > modelo.getPrecioUnitario())
            throw new PrecioUnitarioException();
        ModeloZapato mo = this.modelMapper.map(modelo, ModeloZapato.class);
        mo.setEjemplares(new ArrayList<>());
        this.modeloRepository.save(mo);
    }

    @Override
    public void editarModelo(int id, ModeloZapatoRequestDTO modelo) {

        if (modelo.getCostoTotal() > modelo.getPrecioUnitario())
            throw new PrecioUnitarioException();

        ModeloZapato mo = getModeloZapato(id);
        if (!modelo.getNombre().equals(mo.getNombre())) {
            if (this.modeloRepository.existsByNombreEquals(modelo.getNombre()))
                throw new NombreDeModeloDeZapatoYaExisteException();
        }
        mo = this.modelMapper.map(modelo, ModeloZapato.class);
        mo.setId(id);
        this.modeloRepository.save(mo);
    }

    @Override
    public void eliminarModelo(int id) {
        if (!this.modeloRepository.existsById(id))
            throw new ModeloDeZapatoNoExisteException();
        this.modeloRepository.deleteById(id);
    }

    @Override
    public ModelosDTO listarModelos() {
        List<ModeloZapato> zapatoList = this.modeloRepository.findAll();
        List<ModeloZapatoEnviadoDTO> zapatosEnviados = new ArrayList<>();
        for (ModeloZapato m : zapatoList)
            zapatosEnviados.add(new ModeloZapatoEnviadoDTO(m.getId(), m.getNombre(), (m.getPrecioUnitario() * (100 - m.getDescuento())) / 100, m.getImagenurl()));
        return new ModelosDTO(zapatosEnviados);
    }

    @Override
    public DetalleModeloZapatoDTO getDetalleModeloZapato(int id) {
        ModeloZapato modeloZapato = getModeloZapato(id);
        DetalleModeloZapatoDTO detalleModeloZapatoDTO = this.modelMapper.map(modeloZapato, DetalleModeloZapatoDTO.class);
        detalleModeloZapatoDTO.setPrecioVenta((modeloZapato.getPrecioUnitario() * (100 - modeloZapato.getDescuento())) / 100);
        return detalleModeloZapatoDTO;
    }

    @Override
    public ModelosDTO buscarZapatosPorNombre(String nombre) {
        List<ModeloZapato> zapatoList = this.modeloRepository.findByNombreContains(nombre);
        List<ModeloZapatoEnviadoDTO> zapatosEnviados = new ArrayList<>();
        for (ModeloZapato m : zapatoList)
            zapatosEnviados.add(new ModeloZapatoEnviadoDTO(m.getId(), m.getNombre(), (m.getPrecioUnitario() * (100 - m.getDescuento())) / 100, m.getImagenurl()));
        return new ModelosDTO(zapatosEnviados);
    }

    @Override
    public CostosModeloDTO getCostosModelo(int id) {
        ModeloZapato m = getModeloZapato(id);
        List<Proceso> procesos = this.procesoRepository.findByModeloZapato_IdEquals(id);

        CostosModeloDTO r = new CostosModeloDTO(m.getId(), m.getNombre(), new ArrayList<>());
        int costoTotalModelo = 0;
        for (Proceso p : procesos) {
            costoTotalModelo += p.getCostoTotal();
            CostosProcesoDTO costosProcesoDTO = new CostosProcesoDTO(p.getId(), p.getNombre(), p.getDetalle(), p.getCostoTotal(), new ArrayList<>());
            for (MaterialDeProceso mp : p.getMaterialDeProcesos()) {
                CostosMaterialDTO costosMaterialDTO = new CostosMaterialDTO(
                        mp.getMaterial().getId(),
                        mp.getMaterial().getNombre(),
                        mp.getMaterial().getDetalle(),
                        mp.getMaterial().getUnidad(),
                        mp.getMaterial().getPrecioUnitario(),
                        mp.getCantidad(),
                        mp.getMaterial().getPrecioUnitario() * mp.getCantidad()
                );
                costosProcesoDTO.getMateriales().add(costosMaterialDTO);
            }
            r.getProcesos().add(costosProcesoDTO);
        }
        r.setCostoTotal(costoTotalModelo);
        return r;
    }

    @Override
    public StockModeloDTO consultarStock(int id) {
        ModeloZapato modeloZapato = getModeloZapato(id);
        List<EjemplarDTO> ejemplaresDTO = new ArrayList<>();
        int[] tallas = contarStock(modeloZapato.getEjemplares());
        ejemplaresDTO.add(new EjemplarDTO(tallas[0], "37"));
        ejemplaresDTO.add(new EjemplarDTO(tallas[1], "38"));
        ejemplaresDTO.add(new EjemplarDTO(tallas[2], "39"));
        ejemplaresDTO.add(new EjemplarDTO(tallas[3], "40"));
        ejemplaresDTO.add(new EjemplarDTO(tallas[4], "41"));
        ejemplaresDTO.add(new EjemplarDTO(tallas[5], "42"));
        ejemplaresDTO.add(new EjemplarDTO(tallas[6], "43"));
        return new StockModeloDTO(modeloZapato.getId(), modeloZapato.getNombre(), ejemplaresDTO);
    }

    @Override
    public String actualizarStock(StockModeloDTO payload) {
        if (payload.getEjemplares().size() != 7)
            throw new EjemplaresIncompletosException();
        ModeloZapato modelo = getModeloZapato(payload.getIdModelo());
        int[] tallas = contarStock(modelo.getEjemplares());
        for (EjemplarDTO e : payload.getEjemplares()) {
            int index = 0;
            switch (e.getTalla()) {
                case "37":
                    index = 0;
                    break;
                case "38":
                    index = 1;
                    break;
                case "39":
                    index = 2;
                    break;
                case "40":
                    index = 3;
                    break;
                case "41":
                    index = 4;
                    break;
                case "42":
                    index = 5;
                    break;
                case "43":
                    index = 6;
                    break;
            }
            if (e.getCantidad() > tallas[index])
                aumentarStock(e.getCantidad() - tallas[index], modelo, e.getTalla());
            if (e.getCantidad() < tallas[index])
                disminuirStock(tallas[index] - e.getCantidad(), modelo, e.getTalla());
        }
        this.modeloRepository.save(modelo);
        return "OK";
    }

    private int[] contarStock(List<EjemplarZapato> ejemplares){
        int[] tallas = new int[7];
        for (EjemplarZapato e : ejemplares) {
            switch (e.getTalla()) {
                case "37":
                    tallas[0]++;
                    break;
                case "38":
                    tallas[1]++;
                    break;
                case "39":
                    tallas[2]++;
                    break;
                case "40":
                    tallas[3]++;
                    break;
                case "41":
                    tallas[4]++;
                    break;
                case "42":
                    tallas[5]++;
                    break;
                case "43":
                    tallas[6]++;
                    break;
            }
        }
        return tallas;
    }

    private void aumentarStock(int cantidad, ModeloZapato modelo, String talla) {
        while (cantidad > 0) {
            modelo.getEjemplares().add(new EjemplarZapato(talla,"libre",modelo));
            cantidad--;
        }
    }

    private void disminuirStock(int cantidad, ModeloZapato modelo, String talla) {
        List<EjemplarZapato> borrados = new ArrayList<>();
        for (EjemplarZapato e : modelo.getEjemplares()) {
            if (e.getTalla().equals(talla)) {
                borrados.add(e);
                cantidad--;
                if(cantidad == 0)
                    break;
            }
        }
        for (EjemplarZapato e:borrados) {
            modelo.getEjemplares().remove(e);
            this.ejemplarZapatoRepository.deleteById(e.getId());
        }
    }

    private ModeloZapato getModeloZapato(int id) {
        Optional<ModeloZapato> optional = this.modeloRepository.findById(id);
        if (optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        return optional.get();
    }
}
