package com.codegans.ttp.integration;

import com.codegans.ttp.Block;
import com.codegans.ttp.dsl.java.DefaultTemplateBuilder;
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
        Block block = new DefaultTemplateBuilder().text("ahahaha").build();
    }
}
