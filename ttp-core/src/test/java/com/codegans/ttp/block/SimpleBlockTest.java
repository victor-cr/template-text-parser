package com.codegans.ttp.block;

import com.codegans.ttp.TestUtil;
import com.codegans.ttp.error.ParseException;
import com.codegans.ttp.misc.IntolerantEventBus;
import org.junit.Test;

import static com.codegans.ttp.TestUtil.simple;
import static org.junit.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class SimpleBlockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testContentNull() {
        simple(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContentEmpty() {
        simple("");
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        simple("aaa").apply(null, TestUtil.string("test"), 0);
    }

    @Test
    public void testApply_ExpectedText() {
        int pos = simple("Expected text").apply(IntolerantEventBus.NULL, TestUtil.string("Expected text and something more"), 0);

        assertEquals(13, pos);
    }

    @Test(expected = ParseException.class)
    public void testApply_ExpectedText_Negative() {
        simple("Expected text").apply(IntolerantEventBus.NULL, TestUtil.string("Expected test and something more"), 0);
    }

    @Test
    public void testApply_ExpectedTextOffset() {
        int pos = simple("Expected text").apply(IntolerantEventBus.NULL, TestUtil.string("123Expected text and something more"), 3);

        assertEquals(16, pos);
    }

    @Test(expected = ParseException.class)
    public void testApply_ExpectedTextOffset_Negative() {
        simple("Expected text").apply(IntolerantEventBus.NULL, TestUtil.string("123Expected text and something more"), 2);
    }

    @Test
    public void testApply_ExpectedTextMultiline() {
        int pos = simple("Expected text").apply(IntolerantEventBus.NULL, TestUtil.string("123\nExpected text and something more", 2), 0);

        assertEquals(13, pos);
    }

    @Test(expected = ParseException.class)
    public void testApply_ExpectedTextMultiline_Negative() {
        simple("Expected text").apply(IntolerantEventBus.NULL, TestUtil.string("123\nExpected text and something more", 1), 0);
    }
}
