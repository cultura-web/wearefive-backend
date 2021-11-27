package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoRecibidoDTO;
import com.culturaweb.wearefive.dto.ModelosDTO;
import com.culturaweb.wearefive.service.IModeloZapatoService;
import com.culturaweb.wearefive.service.ModeloZapatoServicelpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class ModeloZapatoController {
    @Autowired
    IModeloZapatoService modeloZapatoService;

    @PostMapping("/products/model")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> agregarModelo(@Valid @RequestBody ModeloZapatoRecibidoDTO payload) {
        this.modeloZapatoService.agregarModelo(payload);
        return new ResponseEntity<>("modelo agregado con éxito", HttpStatus.OK);
    }

    @PutMapping("/products/model/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> editarModelo(@PathVariable(value = "id") int id, @Valid @RequestBody ModeloZapatoRecibidoDTO payload) {
        this.modeloZapatoService.editarModelo(id, payload);
        return new ResponseEntity<>("Editado con éxito", HttpStatus.OK);
    }

    @DeleteMapping("/products/model/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> eliminarModelo(@PathVariable(value = "id") int id) {
        this.modeloZapatoService.eliminarModelo(id);
        return new ResponseEntity<>("Eliminado con éxito", HttpStatus.OK);
    }

    @GetMapping("/products/list")
    public ResponseEntity<ModelosDTO> listarModeloZapatos()
    {
        ModelosDTO r = this.modeloZapatoService.listarModelos();
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @GetMapping("/products/model")
    public ResponseEntity<DetalleModeloZapatoDTO> getDetalleProducto(@RequestParam(name = "idModelo") int id)
    {
        DetalleModeloZapatoDTO r = this.modeloZapatoService.getDetalleModeloZapato(id);
        return new ResponseEntity(r,HttpStatus.OK);
    }

    @GetMapping("/products/model/{nombre}")
    public ResponseEntity<ModelosDTO> buscarModelosZapatos(@PathVariable(value = "nombre") String nombre)
    {
        ModelosDTO r = this.modeloZapatoService.buscarZapatosPorNombre(nombre);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
}
