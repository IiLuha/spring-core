package com.itdev.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(Number id) {
        super();
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
