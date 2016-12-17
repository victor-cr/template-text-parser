package com.codegans.ttp.integration;

import com.codegans.ttp.dsl.java.RegExTemplateBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 21:41
 */
public class RegExTemplateBuilderTest {
    @Test
    public void testBuild_Simple() {
        String expectedReturn = "a+b*";
        String actualReturn = new RegExTemplateBuilder().build("a+b*").compile().toString();

        assertEquals(actualReturn, expectedReturn);
    }

    @Test
    public void testBuild_Optimization() {
        String expectedReturn = "a+b*";
        String actualReturn = new RegExTemplateBuilder().build("(a+b*)").compile().toString();

        assertEquals(actualReturn, expectedReturn);
    }

    @Test
    public void testBuild_Inner() {
        String expectedReturn = "a+(bc)*";
        String actualReturn = new RegExTemplateBuilder().build("(a+(bc)*)").compile().toString();

        assertEquals(actualReturn, expectedReturn);
    }
}
