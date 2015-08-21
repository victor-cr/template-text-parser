package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.error.ParseException;
import com.codegans.ttp.event.TraverseEndEvent;
import com.codegans.ttp.event.TraverseStartEvent;

import java.util.*;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class GroupBlock extends AbstractBlock {
    protected final int minOccurs;
    protected final int maxOccurs;
    protected final Collection<Block> blocks;

    public GroupBlock(int minOccurs, int maxOccurs, Block... blocks) {
        if (blocks == null || blocks.length == 0) {
            throw new IllegalArgumentException("Inner blocks must be defined");
        }

        if (maxOccurs < 0) {
            throw new IllegalArgumentException("Max occurrence cannot be negative: " + maxOccurs);
        }

        if (minOccurs < 0) {
            throw new IllegalArgumentException("Min occurrence cannot be negative: " + minOccurs);
        }

        if (minOccurs > maxOccurs) {
            throw new IllegalArgumentException("Min occurrence is out of bounds. Expected " + minOccurs + " to be within range [0.." + maxOccurs + "]");
        }

        this.blocks = Collections.unmodifiableCollection(new ArrayList<>(Arrays.asList(blocks)));
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
    }

    protected Collection<Block> getBlocks() {
        return blocks;
    }

    @Override
    public int apply(EventBus eventBus, LineStream lines, int offset) {
        Objects.requireNonNull(eventBus, "Event bus is undefined");
        Objects.requireNonNull(lines, "Line stream is undefined");

        int i = offset;

        for (Block block : getBlocks()) {
            eventBus.publish(new TraverseStartEvent(this, lines.getCurrentLineIndex(), i, block));

            try {
                i = block.apply(eventBus, lines, i);
            } catch (ParseException e) {

            } finally {
                eventBus.publish(new TraverseEndEvent(this, lines.getCurrentLineIndex(), i, block));
            }
        }

        return i;
    }
}
