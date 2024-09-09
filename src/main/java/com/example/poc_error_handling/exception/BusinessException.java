package com.example.poc_error_handling.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }


}
