package com.culturaweb.wearefive.exceptions;

public class EjemplarNoExisteException extends RuntimeException{
    public final String MESSAGE = "El ejemplar indicado no se encuentra registrado en la base de datos";

    public EjemplarNoExisteException() {
        super();
    }
}
