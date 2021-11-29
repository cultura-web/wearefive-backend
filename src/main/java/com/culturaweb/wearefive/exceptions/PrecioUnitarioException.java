package com.culturaweb.wearefive.exceptions;

public class PrecioUnitarioException extends RuntimeException{

    public final String MESSAGE = "El costo total de producción del modelo de zapato ingresado es superior al precio unitario. Esto generaría perdida en sus ventas";

    public PrecioUnitarioException() {
        super();
    }
}
