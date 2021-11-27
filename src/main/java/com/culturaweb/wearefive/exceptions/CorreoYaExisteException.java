package com.culturaweb.wearefive.exceptions;

public class CorreoYaExisteException extends RuntimeException{
    public final String MESSAGE = "Correo ingresado ya existe.";

    public CorreoYaExisteException() {
        super();
    }
}
