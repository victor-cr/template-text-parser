package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.GlobalContext;
import com.codegans.ttp.Result;
import com.codegans.ttp.context.LongPositionAwareLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 16:22
 */
public abstract class RangedBlock implements Block<LongPositionAwareLocalContext> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
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

        log.debug("Created an instance: {}", this);
    }

    @Override
    public Result<LongPositionAwareLocalContext> apply(char[] buffer, int offset, int length, GlobalContext context) {
        int end = offset + length;
        int i = offset;
        long j = context.get(this).position();

        while (i < end && j < minOccurs) {
            if (mismatched(buffer, i++, j++)) {
                return Result.fail(new LongPositionAwareLocalContext(i - offset - 1, j - 1));
            }
        }

        while (i < end && j < maxOccurs) {
            if (mismatched(buffer, i++, j++)) {
                return Result.ok(new LongPositionAwareLocalContext(i - offset - 1, j - 1));
            }
        }

        if (j == maxOccurs) {
            return Result.ok(new LongPositionAwareLocalContext(i - offset, j));
        }

        return Result.more(new LongPositionAwareLocalContext(i - offset, j));
    }

    protected abstract boolean mismatched(char[] buffer, int i, long j);

}
