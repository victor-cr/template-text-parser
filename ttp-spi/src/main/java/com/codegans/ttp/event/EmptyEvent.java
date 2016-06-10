package com.codegans.ttp.event;

import com.codegans.ttp.block.Block;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:14
 */
public class EmptyEvent extends Event {
    public EmptyEvent(Block source, long line, int offset) {
        super(source, line, offset);
    }
}
