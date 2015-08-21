package com.codegans.ttp.integration;

import com.codegans.ttp.Block;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.TestUtil;
import com.codegans.ttp.block.DecimalBlock;
import com.codegans.ttp.block.GroupBlock;
import com.codegans.ttp.block.SimpleBlock;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:06
 */
public class SimpleTemplateITest {

    @Test
    public void test() {
        EventBus eventBus = new IntolerantEventBus(NullEventBus.INTSANCE);

        Block block = new GroupBlock(1, 1,
                new SimpleBlock("text "),
                new DecimalBlock(1, 10, true),
                new SimpleBlock(" rest")
        );

        int expected = 20;
        int actual = block.apply(eventBus, TestUtil.string("text 0123456789 rest"), 0);

        assertEquals(expected, actual);
    }
}
