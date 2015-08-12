package com.codegans.ttp.error;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 23:05
 */
public class UnexpectedTokenParserException extends ParserException {
    private final long line;
    private final long column;

    public UnexpectedTokenParserException(long line, long column) {
        super("Unexpected token at " + line + ":" + column);

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
