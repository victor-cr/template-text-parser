package com.codegans.ttp.token;

import com.codegans.ttp.Token;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 14:16
 */
public class StringToken implements Token {
    private final String content;

    public StringToken(String content) {
        this.content = content;
    }

    @Override
    public char[] chars() {
        return content.toCharArray();
    }

    @Override
    public String toString() {
        return content;
    }
}
