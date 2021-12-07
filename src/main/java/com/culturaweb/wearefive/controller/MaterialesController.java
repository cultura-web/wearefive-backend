package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.MaterialRequestDTO;
import com.culturaweb.wearefive.dto.MaterialesDTO;
import com.culturaweb.wearefive.service.IMaterialesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class MaterialesController {

    private final IMaterialesService materialesService;

    public MaterialesController(IMaterialesService materialesService) {
        this.materialesService = materialesService;
    }

    @PostMapping("/materiales")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> agregarMaterial(@Valid @RequestBody MaterialRequestDTO payload) {
        this.materialesService.agregarMaterial(payload);
        return new ResponseEntity<>("Material añadido!", HttpStatus.OK);
    }

    @PutMapping("/materiales/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> editarMaterial(@PathVariable(value = "id") int id, @Valid @RequestBody MaterialRequestDTO payload) {
        this.materialesService.editarMaterial(id,payload);
        return new ResponseEntity<>("Material editado!",HttpStatus.OK);
    }

    @DeleteMapping("/materiales/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> eliminarMaterial(@PathVariable(value = "id") int id) {
        this.materialesService.eliminarMaterial(id);
        return new ResponseEntity<>("Material eliminado!",HttpStatus.OK);
    }

    @GetMapping("/materiales/list")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MaterialesDTO> listarMateriales(){
        MaterialesDTO r = this.materialesService.listarMateriales();
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
}
