package com.codegans.ttp.block;

import com.codegans.ttp.GlobalContext;
import com.codegans.ttp.Result;
import com.codegans.ttp.StaticBlock;
import com.codegans.ttp.context.IntPositionAwareLocalContext;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:04
 */
public class TextBlock implements StaticBlock<IntPositionAwareLocalContext> {
    private final String text;

    public TextBlock(String text) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Text cannot be undefined or empty");
        }

        this.text = text;
    }

    @Override
    public Result<IntPositionAwareLocalContext> apply(char[] buffer, int offset, int length, GlobalContext context) {
        int end = offset + length;
        int len = text.length();
        int i = offset;
        int j = context.get(this).position();

        while (i < end && j < len) {
            if (buffer[i++] != text.charAt(j++)) {
                return Result.fail(new IntPositionAwareLocalContext(i - offset - 1, j - 1));
            }
        }

        if (j == len) {
            return Result.ok(new IntPositionAwareLocalContext(i - offset, j));
        }

        return Result.more(new IntPositionAwareLocalContext(i - offset, j));
    }

    @Override
    public long length() {
        return text.length();
    }
}
