package com.culturaweb.wearefive.exceptions;

public class ModeloDeZapatoNoExisteException extends RuntimeException{
    public final String MESSAGE = "El modelo de zapato ingresado no existe.";

    public ModeloDeZapatoNoExisteException() {
        super();
    }
}
