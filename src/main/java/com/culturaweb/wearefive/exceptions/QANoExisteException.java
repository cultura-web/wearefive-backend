package com.culturaweb.wearefive.exceptions;

public class QANoExisteException extends RuntimeException{
    public final String MESSAGE = "La Q&A ingresada no existe.";

    public QANoExisteException() {
        super();
    }
}
