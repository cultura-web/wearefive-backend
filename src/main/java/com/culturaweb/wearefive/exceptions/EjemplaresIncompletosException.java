package com.culturaweb.wearefive.exceptions;

public class EjemplaresIncompletosException extends RuntimeException{
    public final String MESSAGE = "La cantidad de ejemplares debe ser 7. organizados de la talla 37-43";

    public EjemplaresIncompletosException() {
        super();
    }
}
