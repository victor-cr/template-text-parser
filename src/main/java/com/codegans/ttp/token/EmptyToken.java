package com.codegans.ttp.token;

import com.codegans.ttp.Token;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 14:16
 */
public class EmptyToken implements Token {
    public static final EmptyToken INSTANCE = new EmptyToken();

    private final char[] content = new char[0];

    private EmptyToken() {
    }

    @Override
    public char[] chars() {
        return content;
    }

    @Override
    public String toString() {
        return "<empty>";
    }
}
