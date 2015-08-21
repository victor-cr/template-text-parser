package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.TestUtil;
import org.junit.Test;

import static com.codegans.ttp.TestUtil.group;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 21/08/2015 19:04
 */
public class GroupBlockTest {
    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        group((Block[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        group();
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        group(new NewLineBlock()).apply(null, TestUtil.string("test"), 0);
    }

}
