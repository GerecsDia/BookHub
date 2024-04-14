package com.geolidth.BackEnd.exceptions;

import java.util.NoSuchElementException;

public class NoSuchUserException extends NoSuchElementException {
    public static final String DEFAULT_MESSAGE = "Nincs ilyen felhasználó";

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(Integer id) {
        super(String.format("%s, akinek az id-ja: %d", DEFAULT_MESSAGE, id));
    }
}