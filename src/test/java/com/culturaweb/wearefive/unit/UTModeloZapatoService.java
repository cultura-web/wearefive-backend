package com.culturaweb.wearefive.unit;

import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoEnviadoDTO;
import com.culturaweb.wearefive.dto.ModelosDTO;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.QANoExisteException;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.service.ModeloZapatoServicelpml;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
public class UTModeloZapatoService {

    @InjectMocks
    ModelMapper modelMapper;

    @InjectMocks
    ModeloZapatoServicelpml tested;

    @Mock
    IModeloZapatoRepository modeloZapatoRepository;

    @Test
    public void listarModelosZapatos()
    {
        //arrange
        List<ModeloZapato> zapatoList = new ArrayList<>();
        zapatoList.add(new ModeloZapato(10000,20,"chocolate","prueba.com"));
        zapatoList.add(new ModeloZapato(10000,70,"oro","prueba.com"));
        zapatoList.add(new ModeloZapato(10000,50,"diamante","prueba.com"));

        List<ModeloZapatoEnviadoDTO> modeloZapatos = new ArrayList<>();
        modeloZapatos.add(new ModeloZapatoEnviadoDTO("chocolate", 8000,"prueba.com"));
        modeloZapatos.add(new ModeloZapatoEnviadoDTO("oro", 3000,"prueba.com"));
        modeloZapatos.add(new ModeloZapatoEnviadoDTO("diamante", 5000,"prueba.com"));
        ModelosDTO expected = new ModelosDTO(modeloZapatos);

        when(modeloZapatoRepository.findAll()).thenReturn(zapatoList);
        //act
        ModelosDTO result = this.tested.listarModelos();
        //assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void detallarModeloZapato()
    {
        //arrange
        ModeloZapatoServicelpml t = new ModeloZapatoServicelpml(this.modeloZapatoRepository,new ModelMapper());
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
                "bota");
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
    public void detallarModeloZapatoConIdQueNoExiste()
    {
        //arrange
        when(this.modeloZapatoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        //act - assert
        Assertions.assertThrows(ModeloDeZapatoNoExisteException.class, () -> this.tested.getDetalleModeloZapato(2));
    }
}
