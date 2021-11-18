package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.ModeloZapatoDTO;
import com.culturaweb.wearefive.service.ModeloZapatoServicelpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ModeloZapatoController {
    @Autowired
    ModeloZapatoServicelpml modeloService;

    @PostMapping("/products/model")
    public ResponseEntity<String> agregarModelo(@RequestBody ModeloZapatoDTO payload) {
        this.modeloService.agregarModelo(payload);
        return new ResponseEntity<>("modelo agregado con éxito", HttpStatus.OK);
    }

    @PutMapping("/products/model/{id}")
    public ResponseEntity<String> editarModelo(@PathVariable(value = "id") int id, @RequestBody ModeloZapatoDTO payload) {
        this.modeloService.editarModelo(id, payload);
        return new ResponseEntity<>("Editado con éxito", HttpStatus.OK);

    }

    @DeleteMapping("/products/model/{id}")
    public ResponseEntity<String> eliminarModelo(@PathVariable(value = "id") int id) {
        this.modeloService.eliminarModelo(id);
        return new ResponseEntity<>("Eliminado con éxito", HttpStatus.OK);
    }
}
