package com.codegans.ttp.error;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 23:05
 */
public class UnexpectedTokenParseException extends ParseException {
    public UnexpectedTokenParseException() {
        super("Unexpected token");
    }
}
