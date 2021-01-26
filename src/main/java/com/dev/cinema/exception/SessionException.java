package com.dev.cinema.exception;

public class SessionException extends RuntimeException {
    public SessionException(String message, SessionException e) {
        super(message);
    }
}
