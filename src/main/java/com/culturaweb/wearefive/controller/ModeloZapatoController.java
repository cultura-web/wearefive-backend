package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.CostosModeloDTO;
import com.culturaweb.wearefive.dto.DetalleModeloZapatoDTO;
import com.culturaweb.wearefive.dto.ModeloZapatoRequestDTO;
import com.culturaweb.wearefive.dto.ModelosDTO;
import com.culturaweb.wearefive.service.IModeloZapatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ModeloZapatoController {
    @Autowired
    IModeloZapatoService modeloZapatoService;

    @PostMapping("/model")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> agregarModelo(@Valid @RequestBody ModeloZapatoRequestDTO payload) {
        this.modeloZapatoService.agregarModelo(payload);
        return new ResponseEntity<>("modelo agregado con éxito", HttpStatus.OK);
    }

    @PutMapping("/model/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> editarModelo(@PathVariable(value = "id") int id, @Valid @RequestBody ModeloZapatoRequestDTO payload) {
        this.modeloZapatoService.editarModelo(id, payload);
        return new ResponseEntity<>("Editado con éxito", HttpStatus.OK);
    }

    @DeleteMapping("/model/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> eliminarModelo(@PathVariable(value = "id") int id) {
        this.modeloZapatoService.eliminarModelo(id);
        return new ResponseEntity<>("Eliminado con éxito", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ModelosDTO> listarModeloZapatos()
    {
        ModelosDTO r = this.modeloZapatoService.listarModelos();
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @GetMapping("/model")
    public ResponseEntity<DetalleModeloZapatoDTO> getDetalleProducto(@RequestParam(name = "idModelo") int id)
    {
        DetalleModeloZapatoDTO r = this.modeloZapatoService.getDetalleModeloZapato(id);
        return new ResponseEntity(r,HttpStatus.OK);
    }

    //TODO editar busqueda que no sea case sensitive
    @GetMapping("/model/{nombre}")
    public ResponseEntity<ModelosDTO> buscarModelosZapatos(@PathVariable(value = "nombre") String nombre)
    {
        ModelosDTO r = this.modeloZapatoService.buscarZapatosPorNombre(nombre);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @GetMapping("/model/{idModelo}/costs")
    public ResponseEntity<CostosModeloDTO> getCostoDeUnModelo(@PathVariable(value = "idModelo") int id){
        CostosModeloDTO r = this.modeloZapatoService.getCostosModelo(id);
        return new ResponseEntity(r,HttpStatus.OK);
    }
}
