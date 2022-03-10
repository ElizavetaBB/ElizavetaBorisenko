package com.epam.tc.hw6.service.exception;

public class IllegalBrowserNameException extends IllegalArgumentException {

    public IllegalBrowserNameException(final String message) {
        super(message);
    }
}
