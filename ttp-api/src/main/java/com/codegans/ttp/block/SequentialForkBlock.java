package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.ForkBlock;
import com.codegans.ttp.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 19:07
 */
public class SequentialForkBlock implements ForkBlock {
    private final List<Block> sequence;

    public SequentialForkBlock(List<Block> sequence) {
        this.sequence = new ArrayList<>(sequence);
    }

    @Override
    public int forks() {
        return 1;
    }

    @Override
    public Result apply(char[] buffer, int offset, int length) {
        return null;
    }
}
