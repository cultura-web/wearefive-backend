package com.culturaweb.wearefive.exceptions;

public class ProcesoDeUnModeloYaExisteException extends RuntimeException{
    public final String MESSAGE = "El nombre del proceso indicado, ya existe en ese modelo de zapato";

    public ProcesoDeUnModeloYaExisteException() {
        super();
    }
}
