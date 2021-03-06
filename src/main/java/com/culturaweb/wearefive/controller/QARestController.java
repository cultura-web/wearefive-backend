package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.QAsDTO;
import com.culturaweb.wearefive.service.IQAService;
import com.culturaweb.wearefive.service.QAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class QARestController {

    @Autowired
    IQAService qaService;

    @GetMapping ("/qa")
    public ResponseEntity<QAsDTO> listarQA() {
        QAsDTO qas = this.qaService.listarQA();
        return new ResponseEntity<>(qas , HttpStatus.OK);
    }

    @PostMapping("/qa/new")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> agregarQA(@Valid @RequestBody QADTO payload) {
        this.qaService.agregarQA(payload);
        return new ResponseEntity<>("pregunta y respuesta agregada con éxito", HttpStatus.OK);
    }

    @PutMapping("/qa/edit/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> editarQA(@PathVariable(value = "id") int id, @Valid @RequestBody QADTO payload) {
        this.qaService.editarQA(id, payload);
        return new ResponseEntity<>("pregunta y respuesta editada con éxito", HttpStatus.OK);

    }

    @DeleteMapping("/qa/delete/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> eliminarQA(@PathVariable(value = "id") int id) {
        this.qaService.eliminarQA(id);
        return new ResponseEntity<>("pregunta y respuesta eliminada con éxito", HttpStatus.OK);
    }
}
