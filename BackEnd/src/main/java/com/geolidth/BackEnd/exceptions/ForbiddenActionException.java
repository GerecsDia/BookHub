package com.geolidth.BackEnd.exceptions;

public class ForbiddenActionException extends RuntimeException {

    private final String message;

    public ForbiddenActionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}