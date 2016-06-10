package com.codegans.ttp.block;

import com.codegans.ttp.Result;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 03.04.2016 13:55
 */
public abstract class AbstractBlockTest {

    static String toString(int... codePoints) {
        return new String(codePoints, 0, codePoints.length);
    }

    static Result apply(Block block, String buf, int off) {
        char[] buffer = buf.toCharArray();

        if (off != 0) {
            Result result = block.apply(buffer, 0, off);

            assertEquals(result.getProcessed(), off);
            assertTrue(result.isContinue());
        }

        return block.apply(buffer, off, buffer.length - off);
    }
}
