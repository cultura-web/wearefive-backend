package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.CreacionProcesoDTO;
import com.culturaweb.wearefive.service.IProcesosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products/model")
public class ProcesosController {

    private final IProcesosService procesosService;

    public ProcesosController(IProcesosService procesosService) {
        this.procesosService = procesosService;
    }

    @PostMapping("/{idModelo}/process")
    public ResponseEntity<String> agregarProcesoAModelo(@Valid @RequestBody CreacionProcesoDTO procesoDTO, @PathVariable(value = "idModelo") int idModelo){
        this.procesosService.agregarProcesoAMaterial(procesoDTO,idModelo);
        return new ResponseEntity<>("proceso agregado!",HttpStatus.OK);
    }
}
