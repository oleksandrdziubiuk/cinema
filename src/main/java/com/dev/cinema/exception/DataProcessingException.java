package com.dev.cinema.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, DataProcessingException e) {
        super(message, e);
    }
}
