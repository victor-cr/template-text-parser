package com.codegans.ttp.block;

import com.codegans.ttp.Result;
import com.codegans.ttp.bbb.CharDictionaryBlock;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class CharDictionaryBlockTest {

    // Create tests

    @Test
    public void testCreate_MinNegative() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(-1, 1, "a"));

        assertEquals(exception.getMessage(), "Min/max occurs has to be non-negative numbers");
    }

    @Test
    public void testCreate_MaxNegative() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(1, -1, "a"));

        assertEquals(exception.getMessage(), "Min/max occurs has to be non-negative numbers");
    }

    @Test
    public void testCreate_MaxZero() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(1, 0, "a"));

        assertEquals(exception.getMessage(), "Max occurs has to be positive");
    }

    @Test
    public void testCreate_MinGreater() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(2, 1, "a"));

        assertEquals(exception.getMessage(), "Min occurs cannot be greater than max occurs");
    }

    @Test
    public void testCreate_DictionaryNull() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(1, 2, null));

        assertEquals(exception.getMessage(), "Dictionary cannot be undefined or empty");
    }

    @Test
    public void testCreate_DictionaryEmpty() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(1, 2, ""));

        assertEquals(exception.getMessage(), "Dictionary cannot be undefined or empty");
    }

    @Test
    public void testCreate_DictionaryNonUnique() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharDictionaryBlock(1, 2, "aba"));

        assertEquals(exception.getMessage(), "Duplicated dictionary character: aba");
    }

    // Apply tests

    @Test
    public void testApply_MinFail() {
        Result result = apply(2, 4, "abc", "as", 0, 0);

        assertEquals(result.getParsed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_MinSuccess() {
        Result result = apply(2, 4, "abc", "acid", 0, 0);

        assertEquals(result.getParsed(), 2);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MaxSuccess() {
        Result result = apply(2, 4, "abc", "abba ", 0, 0);

        assertEquals(result.getParsed(), 4);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MiddleSuccess() {
        Result result = apply(2, 4, "abc", "account", 0, 0);

        assertEquals(result.getParsed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply(2, 4, "abc", "abc", 0, 0);

        assertEquals(result.getParsed(), 3);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply(2, 4, "abc", "correct", 0, 3);

        assertEquals(result.getParsed(), 1);
        assertTrue(result.isSuccess());
    }

    private static Result apply(long min, long max, String dictionary, String buf, int off, long pos) {
        return new CharDictionaryBlock(min, max, dictionary).apply(buf.toCharArray(), off, buf.length() - off, pos);
    }
}
