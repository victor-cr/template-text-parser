package com.codegans.ttp.block;

import com.codegans.ttp.ForkBlock;
import com.codegans.ttp.Result;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 19:07
 */
public class ParallelForkBlock implements ForkBlock {
    @Override
    public int forks() {
        return 0;
    }

    @Override
    public Result apply(char[] buffer, int offset, int length) {
        return null;
    }
}
