package com.seirandzhafarov.spring.rest.exceptionHandling;

public class NoSuchEmployeeException extends RuntimeException {

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
