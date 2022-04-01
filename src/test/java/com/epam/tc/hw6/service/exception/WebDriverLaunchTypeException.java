package com.epam.tc.hw6.service.exception;

public class WebDriverLaunchTypeException extends IllegalArgumentException {

    public WebDriverLaunchTypeException(final String message) {
        super(message);
    }
}
