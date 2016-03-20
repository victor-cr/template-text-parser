package com.codegans.ttp.context;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 20:05
 */
public class LongPositionAwareLocalContext extends SimpleLocalContext {
    private final long position;

    public LongPositionAwareLocalContext(int processed, long position) {
        super(processed);

        this.position = position;
    }

    public long position() {
        return position;
    }
}
