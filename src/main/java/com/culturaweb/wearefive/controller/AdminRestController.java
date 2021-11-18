package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.ZapatoDTO;
import com.culturaweb.wearefive.service.IZapatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zapatos")
public class AdminRestController {

    @Autowired
    IZapatosService zapatosService;

    @PostMapping("/new")
    public ResponseEntity<String> agregarNuevoZapato(@RequestBody ZapatoDTO payload)
    {
        this.zapatosService.agregarZapato(payload);
        return new ResponseEntity<>("zapato agregado con éxito",HttpStatus.OK);
    }

}
