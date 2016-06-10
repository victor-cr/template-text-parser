package com.codegans.ttp.block;

import com.codegans.ttp.Result;
import com.codegans.ttp.Stateful;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 16:22
 */
public abstract class RangedBlock implements Block, Stateful {
    private final long minOccurs;
    private final long maxOccurs;
    private long position;
    private long occurs;

    public RangedBlock(long minOccurs, long maxOccurs) {
        if (minOccurs < 0L || maxOccurs < 0L) {
            throw new IllegalArgumentException("Min/max occurs has to be non-negative numbers");
        }

        if (maxOccurs == 0L) {
            throw new IllegalArgumentException("Max occurs has to be positive");
        }

        if (minOccurs > maxOccurs) {
            throw new IllegalArgumentException("Min occurs cannot be greater than max occurs");
        }

        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
    }

    @Override
    public Result apply(char[] buffer, int offset, int length) {
        if (length == 0) {
            return occurs >= minOccurs ? Result.ok(position) : Result.fail(position);
        }

        int limit = offset + length;
        int i = offset;

        while (i < limit && occurs < minOccurs) {
            if (mismatched(buffer, i, position, limit)) {
                return Result.fail(position);
            }

            int shift = Character.charCount(Character.codePointAt(buffer, i, limit));

            i += shift;
            position += shift;
            occurs++;
        }

        if (occurs < minOccurs) {
            return Result.fail(position);
        }

        while (i < limit && occurs < maxOccurs) {
            if (mismatched(buffer, i, position, limit)) {
                return Result.ok(position);
            }

            int shift = Character.charCount(Character.codePointAt(buffer, i, limit));

            i += shift;
            position += shift;
            occurs++;
        }

        if (occurs == maxOccurs) {
            return Result.ok(position);
        }

        return Result.more(position);
    }

    @Override
    public void reset() {
        position = 0L;
        occurs = 0L;
    }

    protected abstract boolean mismatched(char[] buffer, int i, long j, int limit);
}
