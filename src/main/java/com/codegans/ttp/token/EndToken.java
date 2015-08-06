package com.codegans.ttp.token;

import com.codegans.ttp.Token;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 14:16
 */
public class EndToken implements Token {
    public static final EndToken INSTANCE = new EndToken();

    private final char[] content = new char[0];

    private EndToken() {
    }

    @Override
    public char[] chars() {
        return content;
    }

    @Override
    public String toString() {
        return "END";
    }
}
