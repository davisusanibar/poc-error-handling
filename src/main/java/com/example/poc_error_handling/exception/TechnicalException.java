package com.example.poc_error_handling.exception;

public class TechnicalException  extends RuntimeException {
    public TechnicalException(String message) {
        super(message);
    }
}
