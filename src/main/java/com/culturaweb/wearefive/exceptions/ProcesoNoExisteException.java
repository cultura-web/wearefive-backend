package com.culturaweb.wearefive.exceptions;

public class ProcesoNoExisteException extends RuntimeException{

    public final String MESSAGE = "El proceso indicado no existe";

    public ProcesoNoExisteException(){
        super();
    }
}
