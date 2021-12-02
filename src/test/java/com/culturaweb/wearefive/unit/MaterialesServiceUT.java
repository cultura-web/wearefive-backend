package com.culturaweb.wearefive.unit;

import com.culturaweb.wearefive.dto.MaterialRequestDTO;
import com.culturaweb.wearefive.repository.IMaterialRepository;
import com.culturaweb.wearefive.service.MaterialesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    /*@Test
    public void testEditarMaterial(){
        //arrange
        MaterialRequestDTO input = new MaterialRequestDTO("pegamento","pegamento especial para zapatos", 10000,"mililitros");
        when(materialRepository.findById(anyInt())).thenReturn();
        //act
        String result = this.tested.editarMaterial(1,);
        //assert
        Assertions.assertEquals("OK",result);
    }*/
}
