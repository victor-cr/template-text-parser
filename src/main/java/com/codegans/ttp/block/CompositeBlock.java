package com.codegans.ttp.block;

import com.codegans.ttp.LineStream;
import com.codegans.ttp.error.PrematureEndParserException;
import com.codegans.ttp.Block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class CompositeBlock implements Block {
    private final Collection<Block> blocks;

    public CompositeBlock(Collection<Block> blocks) {
        Objects.requireNonNull(blocks);

        this.blocks = new ArrayList<>(blocks);
    }

    @Override
    public int apply(LineStream lines, int offset) {
        boolean premature = blocks.stream().mapToLong(b -> b.apply(lines, 0)).anyMatch(l -> l == -1);

        if (premature) {
            throw new PrematureEndParserException(lines.getCurrentLineIndex(), offset);
        }

        return 0;
    }
}
