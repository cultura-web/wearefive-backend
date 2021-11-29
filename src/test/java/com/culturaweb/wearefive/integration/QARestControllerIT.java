package com.culturaweb.wearefive.integration;


import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QARestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static String token;

    @Test
    public void testAgregarQA() throws Exception {
        //arrange
        JwtUtil jwtUtil = new JwtUtil();
        token = jwtUtil.generateToken(User.builder().username("admin").password("12345678").roles("ADMIN").build());
        QADTO payload = new QADTO("esto es una pregunta","esto es una respuesta");
        String expected = "pregunta y respuesta agregada con éxito";
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();

        String payloadJson = writer.writeValueAsString(payload);

        //act - assert
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/qa/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(expected,response.getResponse().getContentAsString());
    }

    @Test
    public void testAgregarQAConArgumentoDePayloadIncorrecto() throws Exception {
        //arrange
        JwtUtil jwtUtil = new JwtUtil();
        token = jwtUtil.generateToken(User.builder().username("admin").password("12345678").roles("ADMIN").build());
        QADTO payload = new QADTO("esto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una preguntaesto es una pregunta","esto es una respuesta");

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();

        String payloadJson = writer.writeValueAsString(payload);

        //act - assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/qa/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.nombre").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.descripcion").value("no se puede ingresar una pregunta con más de 250 caracteres"));
    }

    @Test
    public void testAgregarQAConPayloadIncorrecto() throws Exception {
        //arrange
        JwtUtil jwtUtil = new JwtUtil();
        token = jwtUtil.generateToken(User.builder().username("admin").password("12345678").roles("ADMIN").build());
        String payload = "payload incorrecto";

        //act - assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/qa/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof HttpMessageNotReadableException))
                .andExpect(jsonPath("$.nombre").value("HttpMessageNotReadableException"));
    }
}
