package com.codegans.ttp.event;

import com.codegans.ttp.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EventObject;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 30.07.2015 20:23
 */
public abstract class Event extends EventObject implements Cloneable {
    protected final Logger log;
    protected final long line;
    protected final int offset;

    public Event(Block source, long line, int offset) {
        super(source);

        this.log = LoggerFactory.getLogger(getClass());
        this.line = line;
        this.offset = offset;


        log.debug("Create an event {} at {}:{}", getClass().getSimpleName(), line, offset);
    }

    @Override
    public Block getSource() {
        return (Block) super.getSource();
    }

    public long getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("Event cloning has failed", e);

            throw new IllegalStateException(e);
        }
    }
}
