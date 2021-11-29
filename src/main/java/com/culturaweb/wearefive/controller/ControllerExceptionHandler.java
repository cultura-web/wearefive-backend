package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.ErrorDTO;
import com.culturaweb.wearefive.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorDTO> credencialesIncorrectas(BadCredentialsException e) {
        ErrorDTO error = new ErrorDTO("CredencialesIncorrectasException", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioYaExisteException.class)
    protected ResponseEntity<ErrorDTO> usernameYaExiste(UsuarioYaExisteException e) {
        ErrorDTO error = new ErrorDTO("UsuarioYaExisteException", e.MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CorreoYaExisteException.class)
    protected ResponseEntity<ErrorDTO> correoYaExiste(CorreoYaExisteException e) {
        ErrorDTO error = new ErrorDTO("CorreoYaExisteException", e.MESSAGE);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
