package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.QADTO;
import com.culturaweb.wearefive.dto.QAsDTO;
import com.culturaweb.wearefive.dto.ZapatoDTO;
import com.culturaweb.wearefive.model.ApiError;
import com.culturaweb.wearefive.model.QA;
import com.culturaweb.wearefive.service.QAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("api")
public class QARestController {

    @Autowired
    QAServiceImpl qaService;

    @GetMapping ("/qa")
    public ResponseEntity<QAsDTO> listarQA() {
        QAsDTO qas = this.qaService.listarQA();
        return new ResponseEntity<>(qas , HttpStatus.OK);
    }

    @PostMapping("/qa/new")
    public ResponseEntity<String> agregarQA(@RequestBody QADTO payload) {
        this.qaService.agregarQA(payload);
        return new ResponseEntity<>("pregunta y respuesta agregadas con éxito", HttpStatus.OK);
    }

    @PutMapping("/qa/edit/{id}")
    public ResponseEntity<String> editarQA(@PathVariable(value = "id") int id, @RequestBody QADTO payload) {
        this.qaService.editarQA(id, payload);
        return new ResponseEntity<>("pregunta y respuesta editada con éxito", HttpStatus.OK);

    }

    @DeleteMapping("/qa/delete/{id}")
    public ResponseEntity<String> eliminarQA(@PathVariable(value = "id") int id) {
        this.qaService.eliminarQA(id);
        return new ResponseEntity<>("pregunta y respuesta eliminada con éxito", HttpStatus.OK);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
