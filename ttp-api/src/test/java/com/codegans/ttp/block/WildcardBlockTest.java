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
public class WildcardBlockTest {

    // Create tests

    @Test
    public void testCreate_DictionaryNull() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new WildcardBlock(null));

        assertEquals(exception.getMessage(), "Stop characters cannot be undefined or empty");
    }

    @Test
    public void testCreate_DictionaryEmpty() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new WildcardBlock(""));

        assertEquals(exception.getMessage(), "Stop characters cannot be undefined or empty");
    }

    @Test
    public void testCreate_DictionaryNonUnique() {
        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () -> new WildcardBlock("aba"));

        assertEquals(exception.getMessage(), "Duplicated stop characters: aba");
    }

    // Apply tests

    @Test
    public void testApply_Success() {
        Result result = apply("\r\n", "Hello world!!!\n", 0, 0);

        assertEquals(result.getProcessed(), 14);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testApply_Continue() {
        Result result = apply("\r\n", "Hello wor", 0, 0);

        assertEquals(result.getProcessed(), 9);
        assertTrue(result.isContinue());
    }

    @Test
    public void testApply_ContinueSuccess() {
        Result result = apply("\r\n", "ld!!!\n", 0, 9);

        assertTrue(result.isSuccess());
        assertEquals(result.getProcessed(), 5);
    }

    private static Result apply(String dictionary, String buf, int off, int pos) {
        return new WildcardBlock(dictionary).apply(buf.toCharArray(), off, buf.length() - off);
    }
}
