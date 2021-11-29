package com.culturaweb.wearefive.exceptions;

public class NombreDeModeloDeZapatoYaExisteException extends RuntimeException {

    public final String MESSAGE = "El nombre de modelo de zapato ingresado ya existe.";

    public NombreDeModeloDeZapatoYaExisteException() {
        super();
    }
}
