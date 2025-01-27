package com.library.exceptions;

public class StudentAlreadyFoundException extends RuntimeException{
    public StudentAlreadyFoundException(String message) {
        super(message);
    }

    // Constructor that accepts both a message and a cause (another exception)
    public StudentAlreadyFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
