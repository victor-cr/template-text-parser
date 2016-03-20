package com.codegans.ttp.block;

import com.codegans.ttp.ForkBlock;
import com.codegans.ttp.GlobalContext;
import com.codegans.ttp.Result;
import com.codegans.ttp.context.SimpleLocalContext;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 19:07
 */
public class ParallelForkBlock implements ForkBlock<SimpleLocalContext> {
    @Override
    public int forks() {
        return 0;
    }

    @Override
    public Result<SimpleLocalContext> apply(char[] buffer, int offset, int length, GlobalContext context) {
        return null;
    }
}
