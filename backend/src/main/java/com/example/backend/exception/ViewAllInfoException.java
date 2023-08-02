package com.example.backend.exception;
public class ViewAllInfoException extends RuntimeException {
    public ViewAllInfoException(String message) {
        super(message);
    }

    public ViewAllInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}

