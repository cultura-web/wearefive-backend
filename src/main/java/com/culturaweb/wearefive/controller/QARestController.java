package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.ZapatoDTO;
import com.culturaweb.wearefive.service.QAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class QARestController {

    @Autowired
    QAServiceImpl qaService;

    @PostMapping("/qa/new")
    public ResponseEntity<String> agregarNuevoZapato(@RequestBody QADTO payload)
    {
        this.qaService.agregarQA(payload);
        return new ResponseEntity<>("pregunta y respuesta agregadas con éxito", HttpStatus.OK);
    }
}
