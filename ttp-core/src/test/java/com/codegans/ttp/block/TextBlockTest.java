package com.codegans.ttp.block;

import com.codegans.ttp.Result;
import com.codegans.ttp.bbb.TextBlock;
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
public class TextBlockTest {

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
        Result result = apply("abc", "as", 0, 0);

        assertEquals(result.getParsed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_Success() {
        Result result = apply("abc", "abcd", 0, 0);

        assertEquals(result.getParsed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply("abc", "ab", 0, 0);

        assertEquals(result.getParsed(), 2);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply("abc", "correct", 0, 2);

        assertEquals(result.getParsed(), 1);
        assertTrue(result.isSuccess());
    }

    private static Result apply(String text, String buf, int off, long pos) {
        return new TextBlock(text).apply(buf.toCharArray(), off, buf.length() - off, pos);
    }
}
