package com.example.backend.exception;

public class LocationInfoException extends RuntimeException {
    public LocationInfoException(String message) {
        super(message);
    }

    public LocationInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}

