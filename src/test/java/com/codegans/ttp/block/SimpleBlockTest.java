package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import org.junit.Test;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class SimpleBlockTest {

    @Test(expected = NullPointerException.class)
    public void testApply_ContentNull() {
        new SimpleBlock(null);
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        new SimpleBlock("", null);
    }

    @Test
    public void testApply_Empty() {
        Block block = new SimpleBlock("");

        block.apply("test");
    }
}
