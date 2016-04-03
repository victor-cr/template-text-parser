package com.codegans.ttp.block;

import com.codegans.ttp.Result;
import com.codegans.ttp.Stateful;
import com.codegans.ttp.StaticBlock;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:04
 */
public class TextBlock implements StaticBlock, Stateful {
    private final char[] text;
    private int position;

    public TextBlock(String text) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Text cannot be undefined or empty");
        }

        this.text = text.toCharArray();
    }

    @Override
    public Result apply(char[] buffer, int offset, int length) {
        if (length == 0) {
            return Result.fail(position);
        }

        int end = offset + length;
        int len = text.length;
        int i = offset;

        while (i < end && position < len) {
            if (buffer[i++] != text[position++]) {
                return Result.fail(--position);
            }
        }

        if (position == len) {
            return Result.ok(position);
        }

        return Result.more(position);
    }

    @Override
    public long length() {
        return text.length;
    }

    @Override
    public void reset() {
        position = 0;
    }
}
