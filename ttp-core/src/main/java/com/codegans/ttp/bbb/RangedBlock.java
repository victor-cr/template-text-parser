package com.codegans.ttp.bbb;

import com.codegans.ttp.DynamicBlock;
import com.codegans.ttp.Result;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 16:22
 */
public abstract class RangedBlock implements DynamicBlock {
    private final long minOccurs;
    private final long maxOccurs;

    public RangedBlock(long minOccurs, long maxOccurs) {
        if (minOccurs < 0 || maxOccurs < 0) {
            throw new IllegalArgumentException("Min/max occurs has to be non-negative numbers");
        }

        if (maxOccurs == 0) {
            throw new IllegalArgumentException("Max occurs has to be positive");
        }

        if (minOccurs > maxOccurs) {
            throw new IllegalArgumentException("Min occurs cannot be greater than max occurs");
        }

        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
    }

    @Override
    public Result apply(char[] buffer, int offset, int length, Result previous) {
        int end = offset + length;
        int i = offset;
        long j = previous.getParsed();

        while (i < end && j < minOccurs) {
            if (mismatched(buffer, i++, j++)) {
                return previous.fail(i - offset - 1);
            }
        }

        while (i < end && j < maxOccurs) {
            if (mismatched(buffer, i++, j++)) {
                return previous.ok(i - offset - 1);
            }
        }

        if (j == maxOccurs) {
            return previous.ok(i - offset);
        }

        return previous.more(i - offset);
    }

    protected abstract boolean mismatched(char[] buffer, int i, long j);
}
