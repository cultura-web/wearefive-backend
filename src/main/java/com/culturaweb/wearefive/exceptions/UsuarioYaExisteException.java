package com.culturaweb.wearefive.exceptions;

public class UsuarioYaExisteException extends RuntimeException{
    public final String MESSAGE = "Username ingresado ya existe.";

    public UsuarioYaExisteException() {
        super();
    }
}
