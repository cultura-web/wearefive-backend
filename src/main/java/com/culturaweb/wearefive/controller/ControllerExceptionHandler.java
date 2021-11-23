package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.ErrorDTO;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.NombreDeModeloDeZapatoYaExisteException;
import com.culturaweb.wearefive.exceptions.PrecioUnitarioException;
import com.culturaweb.wearefive.exceptions.QANoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(HttpMessageNotReadableException e) {
        ErrorDTO error = new ErrorDTO("HttpMessageNotReadableException", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NombreDeModeloDeZapatoYaExisteException.class)
    protected ResponseEntity<ErrorDTO> NombreDeModeloDeZapatoYaExisteException(NombreDeModeloDeZapatoYaExisteException e) {
        ErrorDTO error = new ErrorDTO("NombreDeModeloDeZapatoYaExisteException", e.MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModeloDeZapatoNoExisteException.class)
    protected ResponseEntity<ErrorDTO> ModeloDeZapatoNoExisteException(ModeloDeZapatoNoExisteException e) {
        ErrorDTO error = new ErrorDTO("ModeloDeZapatoNoExisteException", e.MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PrecioUnitarioException.class)
    protected ResponseEntity<ErrorDTO> PrecioUnitarioException(PrecioUnitarioException e) {
        ErrorDTO error = new ErrorDTO("PrecioUnitarioException", e.MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QANoExisteException.class)
    protected ResponseEntity<ErrorDTO> QANoExisteException(QANoExisteException e) {
        ErrorDTO error = new ErrorDTO("QANoExisteException", e.MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
