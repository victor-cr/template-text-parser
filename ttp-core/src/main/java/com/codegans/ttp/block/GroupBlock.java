package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class GroupBlock extends AbstractBlock {
    protected final Collection<Block> blocks;

    public GroupBlock(Collection<Block> blocks) {
        this(new IntolerantEventBus(NullEventBus.INTSANCE), blocks);
    }

    public GroupBlock(EventBus eventBus, Collection<Block> blocks) {
        super(eventBus);

        Objects.requireNonNull(blocks);

        this.blocks = Collections.unmodifiableCollection(new ArrayList<>(blocks));
    }

    protected Collection<Block> getBlocks() {
        return blocks;
    }

    @Override
    public int apply(LineStream lines, int offset) {
        int i = offset;

        for (Block block : getBlocks()) {
            i = block.apply(lines, i);
        }

        return i;
    }
}
