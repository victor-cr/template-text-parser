package com.codegans.ttp.block;

import com.codegans.ttp.TestUtil;
import com.codegans.ttp.error.ParseException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class NewLineBlockTest {

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        new NewLineBlock(null);
    }

    @Test
    public void testApply_Empty() {
        int pos = new NewLineBlock().apply(TestUtil.string("\n\r"), 0);

        assertEquals(0, pos);
    }

    @Test
    public void testApply_Offset() {
        int pos = new NewLineBlock().apply(TestUtil.string("aaaaa\n\r"), 5);

        assertEquals(0, pos);
    }

    @Test(expected = ParseException.class)
    public void testApply_NotFound() {
        new NewLineBlock().apply(TestUtil.string("bla-bla-bla\r\n"), 0);
    }
}
