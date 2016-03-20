package com.codegans.ttp.bbb;

import com.codegans.ttp.Result;
import com.codegans.ttp.StaticBlock;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:04
 */
public class TextBlock implements StaticBlock {
    private final String text;

    public TextBlock(String text) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Text cannot be undefined or empty");
        }

        this.text = text;
    }

    @Override
    public Result apply(char[] buffer, int offset, int length, Result previous) {
        int end = offset + length;
        int len = text.length();
        int i = offset;
        int j = (int) previous.getParsed();

        while (i < end && j < len) {
            if (buffer[i++] != text.charAt(j++)) {
                return previous.fail(i - offset - 1);
            }
        }

        if (j == len) {
            return previous.ok(i - offset);
        }

        return previous.more(i - offset);
    }

    @Override
    public long length() {
        return text.length();
    }
}
