package com.codegans.ttp.block;

import com.codegans.ttp.Result;
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
public class CharsBlockTest extends AbstractBlockTest {

    // Create tests

    @Test
    public void testCreate_MinNegative() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(-1, 1, "a"));

        assertEquals(exception.getMessage(), "Min/max occurs has to be non-negative numbers");
    }

    @Test
    public void testCreate_MaxNegative() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(1, -1, "a"));

        assertEquals(exception.getMessage(), "Min/max occurs has to be non-negative numbers");
    }

    @Test
    public void testCreate_MaxZero() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(1, 0, "a"));

        assertEquals(exception.getMessage(), "Max occurs has to be positive");
    }

    @Test
    public void testCreate_MinGreater() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(2, 1, "a"));

        assertEquals(exception.getMessage(), "Min occurs cannot be greater than max occurs");
    }

    @Test
    public void testCreate_DictionaryNull() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(1, 2, null));

        assertEquals(exception.getMessage(), "Dictionary cannot be undefined or empty");
    }

    @Test
    public void testCreate_DictionaryEmpty() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(1, 2, ""));

        assertEquals(exception.getMessage(), "Dictionary cannot be undefined or empty");
    }

    @Test
    public void testCreate_DictionaryNonUnique() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharsBlock(1, 2, "aba"));

        assertEquals(exception.getMessage(), "Duplicated dictionary character: aba");
    }

    // Apply tests

    @Test
    public void testApply_MinFail() {
        Result result = apply(new CharsBlock(2, 4, "abc"), "as", 0);

        assertEquals(result.getProcessed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_MinSuccess() {
        Result result = apply(new CharsBlock(2, 4, "abc"), "acid", 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MaxSuccess() {
        Result result = apply(new CharsBlock(2, 4, "abc"), "abba ", 0);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MiddleSuccess() {
        Result result = apply(new CharsBlock(2, 4, "abc"), "account", 0);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply(new CharsBlock(2, 4, "abc"), "abc", 0);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply(new CharsBlock(2, 4, "_abc"), "abc_is_a_myth", 3);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }
}
