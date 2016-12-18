package com.codegans.ttp.pattern;

import com.codegans.ttp.template.ConjunctionTemplateFactory;
import com.codegans.ttp.template.DisjunctionTemplateFactory;
import com.codegans.ttp.template.SymbolTemplateFactory;
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
        ProtoNode machine = ConjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').max(3),
                SymbolTemplateFactory.create('b').max(3)
        ).compile();

        assertEquals(machine.toString(), "a{1,3}b{1,3}");
    }

    @Test
    public void testCompile_ConjunctionUnbound() {
        ProtoNode machine = ConjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').unbound(),
                SymbolTemplateFactory.create('b').unbound().min(1),
                SymbolTemplateFactory.create('c').unbound().max(1),
                SymbolTemplateFactory.create('d').min(1).max(1)
        ).compile();

        assertEquals(machine.toString(), "a*b+c?d");
    }

    @Test
    public void testCompile_ConjunctionInner() {
        ProtoNode machine = ConjunctionTemplateFactory.create(
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
        ProtoNode machine = DisjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').max(3),
                SymbolTemplateFactory.create('b').max(3)
        ).compile();

        assertEquals(machine.toString(), "(a{1,3}|b{1,3})");
    }

    @Test
    public void testCompile_DisjunctionUnbound() {
        ProtoNode machine = DisjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').unbound().min(0),
                SymbolTemplateFactory.create('b').unbound().min(1),
                SymbolTemplateFactory.create('c').unbound().max(1),
                SymbolTemplateFactory.create('d').min(1).max(1)
        ).compile();

        assertEquals(machine.toString(), "(a*|b+|c?|d)");
    }
}
