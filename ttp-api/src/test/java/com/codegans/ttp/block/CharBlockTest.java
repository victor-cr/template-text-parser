package com.codegans.ttp.block;

import com.codegans.ttp.Result;
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
public class CharBlockTest extends AbstractBlockTest {

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
        Result result = apply(new CharBlock(2, 4, '='), "=a", 0);

        assertEquals(result.getProcessed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_MinSuccess() {
        Result result = apply(new CharBlock(2, 4, '='), "==a", 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MaxSuccess() {
        Result result = apply(new CharBlock(2, 4, '='), "====a", 0);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MiddleSuccess() {
        Result result = apply(new CharBlock(2, 4, '='), "===a", 0);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply(new CharBlock(2, 4, '='), "==", 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_End() {
        Result result = apply(new CharBlock(2, 4, '='), "===", 3);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply(new CharBlock(2, 4, '='), "====a", 3);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_LongCodePointFail() {
        Result result = apply(new CharBlock(2, 4, 0x10400), toString(0x10400), 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_LongCodePointMin() {
        Result result = apply(new CharBlock(2, 4, 0x10400), toString(0x10400, 0x10400, 'a'), 0);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }
}
