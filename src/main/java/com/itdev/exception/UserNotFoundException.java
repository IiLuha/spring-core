package com.itdev.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Number id) {
        super("User with id " + id + " does not exist.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
