package com.codegans.ttp.block;

import com.codegans.ttp.TestUtil;
import org.junit.Test;

import static com.codegans.ttp.TestUtil.chars;
import static org.junit.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class CharDictionaryBlockTest {

    @Test
    public void testApply_DictionaryNull() {
        chars();
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        new CharDictionaryBlock(null, null);
    }

    @Test
    public void testApply_Empty() {
        int pos = chars().apply(TestUtil.string("test"), 0);

        assertEquals(0, pos);
    }

    @Test
    public void testApply_ExpectedOne() {
        int pos = chars('E', 't').apply(TestUtil.string("Example"), 0);

        assertEquals(1, pos);
    }

    @Test
    public void testApply_ExpectedAll() {
        int pos = chars('E', 't').apply(TestUtil.string("Etc test"), 0);

        assertEquals(2, pos);
    }

    @Test
    public void testApply_ExpectedTextOffset() {
        int pos = chars('E', 't').apply(TestUtil.string("123Expected text and something more"), 3);

        assertEquals(4, pos);
    }

    @Test
    public void testApply_ExpectedTextOffset_Many() {
        int pos = chars('E', 't').apply(TestUtil.string("123EttttttEtEEEEEEEExpected text and something more"), 3);

        assertEquals(20, pos);
    }
}
