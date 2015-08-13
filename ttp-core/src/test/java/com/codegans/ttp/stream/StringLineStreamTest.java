package com.codegans.ttp.stream;

import org.junit.Test;

import static com.codegans.ttp.TestUtil.string;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 11/08/2015 21:06
 */
public class StringLineStreamTest {

    @Test
    public void testCurrentLine() {
        CharSequence expected = "aaa\n";
        CharSequence actual = string("aaa\nbbb\nccc").currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_FirstFetch() {
        CharSequence expected = "aaa\n";
        CharSequence actual = string("aaa\nbbb\nccc", 1).currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_Middle() {
        CharSequence expected = "bbb\n";
        CharSequence actual = string("aaa\nbbb\nccc", 2).currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_Last() {
        CharSequence expected = "ccc";
        CharSequence actual = string("aaa\nbbb\nccc", 3).currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_OutWithoutTerminator() {
        CharSequence actual = string("aaa\nbbb\nccc", 4).currentLine();

        assertNull(actual);
    }

    @Test
    public void testCurrentLine_OutWithTerminator() {
        CharSequence actual = string("aaa\nbbb\nccc\n", 4).currentLine();

        assertNull(actual);
    }

    @Test
    public void testNextLine_FirstFetch() {
        CharSequence expected = "aaa\n";
        CharSequence actual = string("aaa\nbbb\nccc").nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_Middle() {
        CharSequence expected = "bbb\n";
        CharSequence actual = string("aaa\nbbb\nccc", 1).nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_Last() {
        CharSequence expected = "ccc";
        CharSequence actual = string("aaa\nbbb\nccc", 2).nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_OutWithoutTerminator() {
        CharSequence actual = string("aaa\nbbb\nccc", 3).nextLine();

        assertNull(actual);
    }


    @Test
    public void testNextLine_OutWithTerminator() {
        CharSequence actual = string("aaa\nbbb\nccc\n", 3).nextLine();

        assertNull(actual);
    }
}
