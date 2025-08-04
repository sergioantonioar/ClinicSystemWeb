package org.example.clinic_system.handler;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
