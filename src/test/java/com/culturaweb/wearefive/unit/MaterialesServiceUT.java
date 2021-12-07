package com.culturaweb.wearefive.unit;

import com.culturaweb.wearefive.dto.MaterialRequestDTO;
import com.culturaweb.wearefive.model.Material;
import com.culturaweb.wearefive.repository.IMaterialRepository;
import com.culturaweb.wearefive.service.MaterialesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaterialesServiceUT {

    @Mock
    IMaterialRepository materialRepository;

    @InjectMocks
    MaterialesServiceImpl tested;

    @Test
    public void testAgregarMaterial(){
        //arrange
        MaterialRequestDTO input = new MaterialRequestDTO("pegamento","pegamento especial para zapatos", 10000,"mililitros");
        //act
        String result = this.tested.agregarMaterial(input);
        //assert
        Assertions.assertEquals("OK",result);
    }

    @Test
    public void testEditarMaterial(){
        //arrange
        MaterialRequestDTO input = new MaterialRequestDTO("pegamento xxx - 5 mililitros","pegamento especial para la fase de pegado", 15000,"mililitros");

        Material m = new Material("pegamento - 3 mililitros","pegamento especial para la fase de ensamblado",10000,"mililitros");
        Optional<Material> r = Optional.of(m);

        when(materialRepository.findById(anyInt())).thenReturn(r);
        when(materialRepository.save(any(Material.class))).thenReturn(any());
        //act
        String result = this.tested.editarMaterial(1,input);
        //assert
        Assertions.assertEquals("OK",result);
    }

    @Test
    public void testEliminarMaterial(){
        //arrange
        when(this.materialRepository.existsById(anyInt())).thenReturn(true);
        //act
        String result = this.tested.eliminarMaterial(1);
        //assert
        Assertions.assertEquals("OK",result);
    }
}
