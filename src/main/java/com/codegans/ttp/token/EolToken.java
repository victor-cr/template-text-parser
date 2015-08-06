package com.codegans.ttp.token;

import com.codegans.ttp.Token;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:48
 */
public enum EolToken implements Token {
    CRLF("\r\n"),
    LFCR("\n\r"),
    CR("\r"),
    LF("\n"),
    SYSTEM(System.getProperty("line.terminator"));

    private final String terminator;

    EolToken(String terminator) {
        this.terminator = terminator;
    }

    public String getTerminator() {
        return terminator;
    }

    @Override
    public char[] chars() {
        return terminator.toCharArray();
    }

    public static EolToken[] any() {
        return new EolToken[] {CRLF, LFCR, CR, LF};
    }
}
