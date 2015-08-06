package com.codegans.ttp.event;

import com.codegans.ttp.Event;
import com.codegans.ttp.error.ParserException;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 30.07.2015 20:28
 */
public class ErrorEvent implements Event {
    private final ParserException exception;

    public ErrorEvent(ParserException exception) {
        this.exception = exception;
    }

    public ParserException getException() {
        return exception;
    }
}
