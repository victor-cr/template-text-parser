package com.codegans.ttp.error;

import com.codegans.ttp.misc.StringUtil;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 23:05
 */
public class UnexpectedTokenParseException extends ParseException {
    private final CharSequence expected;

    public UnexpectedTokenParseException(CharSequence expected) {
        super("Unexpected token. Expected: " + StringUtil.encodeSpecial(expected));

        this.expected = expected;
    }

    public CharSequence getExpected() {
        return expected;
    }
}
