package com.codegans.ttp.error;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 23:05
 */
public class UnexpectedTokenParseException extends ParseException {
    private final long line;
    private final int offset;
    private final CharSequence expected;
    private final CharSequence actual;

    public UnexpectedTokenParseException(long line, int offset, CharSequence expected, CharSequence actual) {
        super("Unexpected token. Expected: '" + expected + "', but was: '" + actual + "'");

        this.line = line;
        this.offset = offset;
        this.expected = expected;
        this.actual = actual;
    }

    public long getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }

    public CharSequence getExpected() {
        return expected;
    }

    public CharSequence getActual() {
        return actual;
    }
}
