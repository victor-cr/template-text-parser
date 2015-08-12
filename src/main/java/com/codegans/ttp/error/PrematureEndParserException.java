package com.codegans.ttp.error;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 23:05
 */
public class PrematureEndParserException extends ParserException {
    private final long line;
    private final long column;

    public PrematureEndParserException(long line, long column) {
        super("Premature end of the input at " + line + ":" + column);

        this.line = line;
        this.column = column;
    }

    public long getLine() {
        return line;
    }

    public long getColumn() {
        return column;
    }
}
