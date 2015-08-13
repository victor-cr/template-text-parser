package com.codegans.ttp.integration;

import com.codegans.ttp.Block;
import com.codegans.ttp.misc.DefaultTemplateBuilder;
import com.codegans.ttp.misc.NullEventBus;
import org.junit.Test;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:06
 */
public class SimpleDslITest {

    @Test
    public void test() {
        Block block = DefaultTemplateBuilder.get().bus(NullEventBus.INTSANCE)
                .add(DefaultTemplateBuilder.text(""))
                .build();
    }
}
