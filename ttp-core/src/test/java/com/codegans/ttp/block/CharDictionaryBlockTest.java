package com.codegans.ttp.block;

import com.codegans.ttp.TestUtil;
import com.codegans.ttp.misc.IntolerantEventBus;
import org.junit.Test;

import static com.codegans.ttp.TestUtil.chars;
import static com.codegans.ttp.TestUtil.charsOneOrMore;
import static org.junit.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class CharDictionaryBlockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testApply_DictionaryNull() {
        charsOneOrMore((char[]) null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testApply_DictionaryEmpty() {
        charsOneOrMore();
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        charsOneOrMore('a').apply(null, TestUtil.string("test"), 0);
    }

    @Test
    public void testApply_ExpectedOne() {
        int pos = charsOneOrMore('E', 't').apply(IntolerantEventBus.NULL, TestUtil.string("Example"), 0);

        assertEquals(1, pos);
    }

    @Test
    public void testApply_ExpectedAll() {
        int pos = charsOneOrMore('E', 't').apply(IntolerantEventBus.NULL, TestUtil.string("Etc test"), 0);

        assertEquals(2, pos);
    }

    @Test
    public void testApply_ExpectedTextOffset() {
        int pos = charsOneOrMore('E', 't').apply(IntolerantEventBus.NULL, TestUtil.string("123Expected text and something more"), 3);

        assertEquals(4, pos);
    }

    @Test
    public void testApply_ExpectedTextOffset_Many() {
        int pos = charsOneOrMore('E', 't').apply(IntolerantEventBus.NULL, TestUtil.string("123EttttttEtEEEEEEEExpected text and something more"), 3);

        assertEquals(20, pos);
    }

    @Test
    public void testApply_MinOccurs() {
        int pos = chars(2, Integer.MAX_VALUE, '1', '2').apply(IntolerantEventBus.NULL, TestUtil.string("12345678"), 0);

        assertEquals(2, pos);
    }

    @Test
    public void testApply_MaxOccurs() {
        int pos = chars(0, 3, '1', '2').apply(IntolerantEventBus.NULL, TestUtil.string("12121212"), 0);

        assertEquals(3, pos);
    }
}
