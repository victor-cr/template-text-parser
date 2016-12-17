package com.codegans.ttp.pattern;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 18:50
 */
public class TemplateFactoryTest {
    @Test
    public void testCompile_ConjunctionBound() {
        TemplateInstance machine = ConjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').max(3),
                SymbolTemplateFactory.create('b').max(3)
        ).compile();

        assertEquals(machine.toString(), "a{1,3}b{1,3}");
    }

    @Test
    public void testCompile_ConjunctionUnbound() {
        TemplateInstance machine = ConjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').unbound(),
                SymbolTemplateFactory.create('b').unbound().min(1),
                SymbolTemplateFactory.create('c').unbound().max(1),
                SymbolTemplateFactory.create('d').min(1).max(1)
        ).compile();

        assertEquals(machine.toString(), "a*b+c?d");
    }

    @Test
    public void testCompile_ConjunctionInner() {
        TemplateInstance machine = ConjunctionTemplateFactory.create(
                ConjunctionTemplateFactory.create(
                        SymbolTemplateFactory.create('a').unbound(),
                        SymbolTemplateFactory.create('b').unbound().min(1)
                ).unbound(),
                SymbolTemplateFactory.create('d').min(1).max(1)
        ).compile();

        assertEquals(machine.toString(), "(a*b+)*d");
    }

    @Test
    public void testCompile_DisjunctionBound() {
        TemplateInstance machine = DisjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').max(3),
                SymbolTemplateFactory.create('b').max(3)
        ).compile();

        assertEquals(machine.toString(), "(a{1,3}|b{1,3})");
    }

    @Test
    public void testCompile_DisjunctionUnbound() {
        TemplateInstance machine = DisjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').unbound().min(0),
                SymbolTemplateFactory.create('b').unbound().min(1),
                SymbolTemplateFactory.create('c').unbound().max(1),
                SymbolTemplateFactory.create('d').min(1).max(1)
        ).compile();

        assertEquals(machine.toString(), "(a*|b+|c?|d)");
    }
}
