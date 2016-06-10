package com.codegans.ttp.event;

import com.codegans.ttp.block.Block;
import com.codegans.ttp.error.ParseException;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 30.07.2015 20:28
 */
public class ErrorEvent extends Event {
    private final ParseException exception;

    public ErrorEvent(Block source, long line, int offset, ParseException exception) {
        super(source, line, offset);

        this.exception = exception;
    }

    public ParseException getException() {
        return exception;
    }
}
