package com.codegans.ttp.block;

import com.codegans.ttp.TestUtil;
import com.codegans.ttp.error.ParseException;
import com.codegans.ttp.misc.IntolerantEventBus;
import org.junit.Test;

import static com.codegans.ttp.TestUtil.dictionary;
import static org.junit.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class DictionaryBlockTest {

    @Test(expected = IllegalArgumentException.class)
    public void testDictionaryNull() {
        dictionary((CharSequence[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDictionaryEmpty() {
        dictionary();
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        dictionary("aa").apply(null, TestUtil.string("test"), 0);
    }

    @Test
    public void testApply_Expected() {
        int pos = dictionary("Dave", "Jan").apply(IntolerantEventBus.NULL, TestUtil.string("Dave Dupre"), 0);

        assertEquals(4, pos);
    }

    @Test
    public void testApply_ExpectedLongest() {
        int pos = dictionary("Jan", "January", "Januarissimo").apply(IntolerantEventBus.NULL, TestUtil.string("January, 28"), 0);

        assertEquals(7, pos);
    }

    @Test
    public void testApply_ExpectedTextOffset() {
        int pos = dictionary("Jean", "Jeanette", "Jean-Claude").apply(IntolerantEventBus.NULL, TestUtil.string("123Jean-Claude Van Damme"), 3);

        assertEquals(14, pos);
    }

    @Test(expected = ParseException.class)
    public void testApply_ExpectedTextOffset_Negative() {
        dictionary("Jean", "Jeanette", "Jean-Claude").apply(IntolerantEventBus.NULL, TestUtil.string("123Jean-Claude Van Damme"), 2);
    }
}
