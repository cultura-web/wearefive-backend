package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.ZapatoDTO;
import com.culturaweb.wearefive.service.QAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class QARestController {

    @Autowired
    QAServiceImpl qaService;

    @PostMapping("/q&a/new")
    public ResponseEntity<String> agregarNuevoZapato(@RequestBody QADTO payload)
    {
        this.qaService.agregarQA(payload);
        return new ResponseEntity<>("pregunta y respuesta agregadas con éxito", HttpStatus.OK);
    }
    @PutMapping("/q&a/edit/{id}")
    public ResponseEntity<String> editarNuevoZapato(@PathVariable(value = "id") int id,@RequestBody QADTO payload)
    {
        return new ResponseEntity<>("ID: " + id, HttpStatus.OK);
        //this.qaService.editarQA(Id,payload);
        //return new ResponseEntity<>("pregunta y respuesta editada con éxito", HttpStatus.OK);

    }
        @DeleteMapping("/q&a/delete/{id}")
   public ResponseEntity<String> eliminarNuevoZapato(@PathVariable(value = "id") int id)
   {
       this.qaService.eliminarQA(id);
       return new ResponseEntity<>("pregunta y respuesta eliminada con éxito", HttpStatus.OK);
   }
}
