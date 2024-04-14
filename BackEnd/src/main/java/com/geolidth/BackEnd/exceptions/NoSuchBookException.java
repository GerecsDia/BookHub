package com.geolidth.BackEnd.exceptions;

import java.util.NoSuchElementException;

public class NoSuchBookException extends NoSuchElementException {

    public static final String MESSAGE = "Ez a könyv nem található.";

    public NoSuchBookException() {
        super(MESSAGE);

    }
}
