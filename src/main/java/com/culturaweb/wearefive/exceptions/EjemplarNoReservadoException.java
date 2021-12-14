package com.culturaweb.wearefive.exceptions;

public class EjemplarNoReservadoException extends RuntimeException{
    public final String MESSAGE = "El ejemplar indicado no ha sido reservado";

    public EjemplarNoReservadoException() {
        super();
    }
}
