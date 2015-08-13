package com.codegans.ttp.stream;

import com.codegans.ttp.Terminator;
import org.junit.Test;

import java.util.Arrays;

import static com.codegans.ttp.TestUtil.reader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 11/08/2015 21:06
 */
public class ReaderLineStreamTest {

    @Test
    public void testCurrentLine() {
        CharSequence expected = "aaa\n";
        CharSequence actual = reader("aaa\nbbb\nccc").currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_FirstFetch() {
        CharSequence expected = "aaa\n";
        CharSequence actual = reader("aaa\nbbb\nccc", 1).currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_Middle() {
        CharSequence expected = "bbb\n";
        CharSequence actual = reader("aaa\nbbb\nccc", 2).currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_Last() {
        CharSequence expected = "ccc";
        CharSequence actual = reader("aaa\nbbb\nccc", 3).currentLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentLine_OutWithoutTerminator() {
        CharSequence actual = reader("aaa\nbbb\nccc", 4).currentLine();

        assertNull(actual);
    }

    @Test
    public void testCurrentLine_OutWithTerminator() {
        CharSequence actual = reader("aaa\nbbb\nccc\n", 4).currentLine();

        assertNull(actual);
    }

    @Test
    public void testNextLine_FirstFetch() {
        CharSequence expected = "aaa\n";
        CharSequence actual = reader("aaa\nbbb\nccc").nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_Middle() {
        CharSequence expected = "bbb\n";
        CharSequence actual = reader("aaa\nbbb\nccc", 1).nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_Last() {
        CharSequence expected = "ccc";
        CharSequence actual = reader("aaa\nbbb\nccc", 2).nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_OutWithoutTerminator() {
        CharSequence actual = reader("aaa\nbbb\nccc", 3).nextLine();

        assertNull(actual);
    }


    @Test
    public void testNextLine_OutWithTerminator() {
        CharSequence actual = reader("aaa\nbbb\nccc\n", 3).nextLine();

        assertNull(actual);
    }

    @Test
    public void testNextLine_TerminatorSplit() {
        StringBuilder text = new StringBuilder(8200);
        char[] arr = new char[8191];

        Arrays.fill(arr, 'a');

        text.append(arr).append(Terminator.CRLF.value()).append("aaaaa").append(Terminator.CRLF.value());

        CharSequence expected = "aaaaa\r\n";
        CharSequence actual = reader(text.toString(), 1, Terminator.CRLF).nextLine();

        assertEquals(expected, actual);
    }

    @Test
    public void testNextLine_LongTerminatorSplit() {
        StringBuilder text = new StringBuilder(8200);
        char[] arr = new char[8191];

        Arrays.fill(arr, 'a');

        text.append(arr).append(Terminator.CRLF.value()).append("aaaaa").append(Terminator.CRLF.value());

        CharSequence expected = "aaaaa\r\n";
        CharSequence actual = reader(text.toString(), 1, Terminator.CRLF, Terminator.CR).nextLine();

        assertEquals(expected, actual);
    }
}
