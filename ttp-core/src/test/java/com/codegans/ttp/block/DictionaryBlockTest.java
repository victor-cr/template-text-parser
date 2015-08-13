package com.codegans.ttp.block;

import com.codegans.ttp.TestUtil;
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

    @Test
    public void testApply_DictionaryNull() {
        dictionary();
    }

    @Test(expected = NullPointerException.class)
    public void testApply_EventBusNull() {
        new CharDictionaryBlock(null, null);
    }

    @Test
    public void testApply_Empty() {
        int pos = dictionary().apply(TestUtil.string("test"), 0);

        assertEquals(0, pos);
    }

    @Test
    public void testApply_Expected() {
        int pos = dictionary("Dave", "Jan").apply(TestUtil.string("Dave Dupre"), 0);

        assertEquals(4, pos);
    }

    @Test
    public void testApply_ExpectedLongest() {
        int pos = dictionary("Jan", "January", "Januarissimo").apply(TestUtil.string("January, 28"), 0);

        assertEquals(7, pos);
    }

    @Test
    public void testApply_ExpectedTextOffset() {
        int pos = dictionary("Jean", "Jeanette", "Jean-Claude").apply(TestUtil.string("123Jean-Claude Van Damme"), 3);

        assertEquals(14, pos);
    }
}
