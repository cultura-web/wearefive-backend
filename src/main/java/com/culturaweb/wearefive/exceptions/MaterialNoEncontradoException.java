package com.culturaweb.wearefive.exceptions;

public class MaterialNoEncontradoException extends RuntimeException{
    public final String MESSAGE = "El material indicado no existe";

    public MaterialNoEncontradoException() {
        super();
    }
}
