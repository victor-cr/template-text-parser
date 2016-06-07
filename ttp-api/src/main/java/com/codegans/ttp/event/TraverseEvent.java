package com.codegans.ttp.event;

import com.codegans.ttp.Block;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 21/08/2015 15:42
 */
public abstract class TraverseEvent extends Event {
    private final Block delegate;

    public TraverseEvent(Block source, long line, int offset, Block delegate) {
        super(source, line, offset);
        this.delegate = delegate;
    }

    public Block getDelegate() {
        return delegate;
    }
}
