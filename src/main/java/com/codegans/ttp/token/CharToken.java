package com.codegans.ttp.token;

import com.codegans.ttp.Token;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 14:16
 */
public class CharToken implements Token {
    private final char content;

    public CharToken(char content) {
        this.content = content;
    }

    public CharToken(int content) {
        this.content = (char) content;
    }

    @Override
    public char[] chars() {
        return new char[]{content};
    }

    @Override
    public String toString() {
        return String.valueOf(content);
    }
}
