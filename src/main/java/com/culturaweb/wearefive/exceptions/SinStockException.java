package com.culturaweb.wearefive.exceptions;

public class SinStockException extends RuntimeException {
    public final String MESSAGE = "El modelo indicado no presenta stock para la talla ingresada";

    public SinStockException() {
        super();
    }
}
