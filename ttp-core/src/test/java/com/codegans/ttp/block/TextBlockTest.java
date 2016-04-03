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
public class TextBlockTest extends AbstractBlockTest {

    // Create tests

    @Test
    public void testCreate_Null() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new TextBlock(null));

        assertEquals(exception.getMessage(), "Text cannot be undefined or empty");
    }

    @Test
    public void testCreate_Empty() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new TextBlock(""));

        assertEquals(exception.getMessage(), "Text cannot be undefined or empty");
    }

    // Apply tests

    @Test
    public void testApply_Fail() {
        Result result = apply(new TextBlock("abc"), "as", 0);

        assertEquals(result.getProcessed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_Success() {
        Result result = apply(new TextBlock("abc"), "abcd", 0);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply(new TextBlock("abc"), "ab", 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_End() {
        Result result = apply(new TextBlock("abc"), "ab", 2);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply(new TextBlock("abc"), "abcorrect", 2);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_LongCodePointFail() {
        Result result = apply(new TextBlock(toString('a', 0x10400, 'c')), "abc", 0);

        assertEquals(result.getProcessed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_LongCodePointSuccess() {
        Result result = apply(new TextBlock(toString('a', 0x10400, 'c')), toString('a', 0x10400, 'c', 'd'), 0);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }
}
