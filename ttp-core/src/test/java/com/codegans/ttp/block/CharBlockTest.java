package com.codegans.ttp.block;

import com.codegans.ttp.Result;
import com.codegans.ttp.bbb.CharBlock;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:32
 */
public class CharBlockTest {

    // Create tests

    @Test
    public void testCreate_MinNegative() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharBlock(-1, 1, 'a'));

        assertEquals(exception.getMessage(), "Min/max occurs has to be non-negative numbers");
    }

    @Test
    public void testCreate_MaxNegative() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharBlock(1, -1, 'a'));

        assertEquals(exception.getMessage(), "Min/max occurs has to be non-negative numbers");
    }

    @Test
    public void testCreate_MaxZero() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharBlock(1, 0, 'a'));

        assertEquals(exception.getMessage(), "Max occurs has to be positive");
    }

    @Test
    public void testCreate_MinGreater() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharBlock(2, 1, 'a'));

        assertEquals(exception.getMessage(), "Min occurs cannot be greater than max occurs");
    }

    @Test
    public void testCreate_Undefined() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new CharBlock(1, 2, 0xfa00fa));

        assertEquals(exception.getMessage(), "The code point 16384250 is undefined");
    }

    @Test
    public void testCreate_Defined() {
        assertNotNull(new CharBlock(1, 2, 0x10400));
    }

    // Apply tests

    @Test
    public void testApply_MinFail() {
        Result result = apply(2, 4, '=', "=a", 0, 0);

        assertEquals(result.getParsed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_MinSuccess() {
        Result result = apply(2, 4, '=', "==a", 0, 0);

        assertEquals(result.getParsed(), 2);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MaxSuccess() {
        Result result = apply(2, 4, '=', "====a", 0, 0);

        assertEquals(result.getParsed(), 4);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MiddleSuccess() {
        Result result = apply(2, 4, '=', "===a", 0, 0);

        assertEquals(result.getParsed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply(2, 4, '=', "==", 0, 0);

        assertEquals(result.getParsed(), 2);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply(2, 4, '=', "=a", 0, 3);

        assertEquals(result.getParsed(), 1);
        assertTrue(result.isSuccess());
    }

    private static Result apply(long min, long max, int ch, String buf, int off, long pos) {
        return new CharBlock(min, max, ch).apply(buf.toCharArray(), off, buf.length() - off, pos);
    }
}
