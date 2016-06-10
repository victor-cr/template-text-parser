package com.codegans.ttp.event;

import com.codegans.ttp.block.Block;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 21/08/2015 15:42
 */
public class TraverseEndEvent extends TraverseEvent {
    public TraverseEndEvent(Block source, long line, int offset, Block delegate) {
        super(source, line, offset, delegate);
    }
}
