package com.codegans.ttp.error;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:50
 */
public abstract class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
