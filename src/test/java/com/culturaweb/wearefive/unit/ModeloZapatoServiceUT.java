package com.culturaweb.wearefive.unit;

import com.culturaweb.wearefive.dto.*;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.repository.IProcesoRepository;
import com.culturaweb.wearefive.service.ModeloZapatoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModeloZapatoServiceUT {

    @InjectMocks
    ModeloZapatoServiceImpl tested;

    @Mock
    IModeloZapatoRepository modeloZapatoRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    IProcesoRepository procesoRepository;

    @Test
    public void testListarModelosZapatos()
    {
        //arrange
        List<ModeloZapato> zapatoList = new ArrayList<>();
        zapatoList.add(new ModeloZapato(1,10000,20,"chocolate","prueba.com"));
        zapatoList.add(new ModeloZapato(2,10000,70,"oro","prueba.com"));
        zapatoList.add(new ModeloZapato(3,10000,50,"diamante","prueba.com"));

        List<ModeloZapatoEnviadoDTO> modeloZapatos = new ArrayList<>();
        modeloZapatos.add(new ModeloZapatoEnviadoDTO(1,"chocolate", 8000,"prueba.com"));
        modeloZapatos.add(new ModeloZapatoEnviadoDTO(2,"oro", 3000,"prueba.com"));
        modeloZapatos.add(new ModeloZapatoEnviadoDTO(3,"diamante", 5000,"prueba.com"));
        ModelosDTO expected = new ModelosDTO(modeloZapatos);

        when(modeloZapatoRepository.findAll()).thenReturn(zapatoList);
        //act
        ModelosDTO result = this.tested.listarModelos();
        //assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testDetallarModeloZapato()
    {
        //arrange
        ModeloZapatoServiceImpl t = new ModeloZapatoServiceImpl(this.modeloZapatoRepository,null,null, new ModelMapper());
        ModeloZapato m = new ModeloZapato(
                1,
                "marron",
                4000,
                10000,
                20,
                "chocolate",
                "un zapato unico...",
                "tener cuidado con el armado...",
                "algodon",
                "prueba.com",
                "bota",
                null,
                null,
                null);
        Optional<ModeloZapato> optional = Optional.of(m);
        when(this.modeloZapatoRepository.findById(anyInt())).thenReturn(optional);

        DetalleModeloZapatoDTO expected = new DetalleModeloZapatoDTO(
                "chocolate",
                8000,
                "prueba.com",
                "un zapato unico...",
                "marron",
                "bota",
                "algodon");
        //act
        DetalleModeloZapatoDTO result = t.getDetalleModeloZapato(1);
        //assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testDetallarModeloZapatoConIdQueNoExiste()
    {
        //arrange
        when(this.modeloZapatoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        //act - assert
        Assertions.assertThrows(ModeloDeZapatoNoExisteException.class, () -> this.tested.getDetalleModeloZapato(2));
    }

    @Test
    public void testBuscarModelosPorNombre()
    {
        //arrange
        List<ModeloZapatoEnviadoDTO> list = new ArrayList<>();
        list.add(new ModeloZapatoEnviadoDTO(1,"hielo",9000,"ejemplo.com"));
        list.add(new ModeloZapatoEnviadoDTO(2,"cielo",8000,"ejemplo.com"));

        ModelosDTO expected = new ModelosDTO(list);

        List<ModeloZapato> mocked = new ArrayList();
        ModeloZapato m1 = new ModeloZapato(1,10000,10,"hielo","ejemplo.com");
        ModeloZapato m2 = new ModeloZapato(2,10000,20,"cielo","ejemplo.com");
        mocked.add(m1);
        mocked.add(m2);

        when(this.modeloZapatoRepository.findByNombreContains(anyString())).thenReturn(mocked);
        //act
        ModelosDTO result = this.tested.buscarZapatosPorNombre("elo");
        //assert
        Assertions.assertEquals(expected,result);

    }
}
