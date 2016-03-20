package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.GlobalContext;
import com.codegans.ttp.LocalContext;
import com.codegans.ttp.Result;
import com.codegans.ttp.context.LongPositionAwareLocalContext;
import org.testng.annotations.Test;

import java.util.function.Supplier;

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
        Result<LongPositionAwareLocalContext> result = apply(2, 4, '=', "=a", 0, 0);

        assertEquals(result.getProcessed(), 1);
        assertTrue(result.isFail());
    }

    @Test
    public void testApply_MinSuccess() {
        Result<LongPositionAwareLocalContext> result = apply(2, 4, '=', "==a", 0, 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MaxSuccess() {
        Result<LongPositionAwareLocalContext> result = apply(2, 4, '=', "====a", 0, 0);

        assertEquals(result.getProcessed(), 4);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_MiddleSuccess() {
        Result<LongPositionAwareLocalContext> result = apply(2, 4, '=', "===a", 0, 0);

        assertEquals(result.getProcessed(), 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result<LongPositionAwareLocalContext> result = apply(2, 4, '=', "==", 0, 0);

        assertEquals(result.getProcessed(), 2);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result<LongPositionAwareLocalContext> result = apply(2, 4, '=', "=a", 0, 3);

        assertTrue(result.isSuccess());
        assertEquals(result.getProcessed(), 1);
    }

    private static Result<LongPositionAwareLocalContext> apply(long min, long max, int ch, String buf, int off, long pos) {
        return new CharBlock(min, max, ch).apply(buf.toCharArray(), off, buf.length() - off, new GenericGlobalContext(() -> new LongPositionAwareLocalContext(0, pos)));
    }

    private static class GenericGlobalContext implements GlobalContext {
        private final Supplier<LocalContext> supplier;

        public GenericGlobalContext(Supplier<LocalContext> supplier) {
            this.supplier = supplier;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T extends LocalContext> T get(Block<T> block) {
            return (T) supplier.get();
        }
    }
}
