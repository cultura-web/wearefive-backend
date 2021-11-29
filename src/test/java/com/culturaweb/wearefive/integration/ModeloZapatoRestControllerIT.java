package com.culturaweb.wearefive.integration;

import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ModeloZapatoRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDetalleProducto() throws Exception {
        //arrange

        //act-assert
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/products/model?idModelo=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nombre").value("ITADORI"))
                .andExpect(jsonPath("$.precioVenta").value(5000))
                .andExpect(jsonPath("$.imagenUrl").value("ejemplo.com"))
                .andExpect(jsonPath("$.descripcion").value("Economico y artesanal, proporciona a tus pies la sensación de caminar en el cielo"))
                .andExpect(jsonPath("$.color").value("negro"))
                .andExpect(jsonPath("$.tipo").value("bota"))
                .andExpect(jsonPath("$.material").value("cuero"));
    }

    @Test
    public void testGetDetalleProductoInexistente() throws Exception {
        //arrange

        //act-assert
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/products/model?idModelo=10"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.nombre").value("ModeloDeZapatoNoExisteException"))
                .andExpect(jsonPath("$.descripcion").value("El modelo de zapato ingresado no existe."));
    }
}
