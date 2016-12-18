package com.codegans.ttp.transformer;

import com.codegans.ttp.pattern.ProtoNode;
import com.codegans.ttp.template.ConjunctionTemplateFactory;
import com.codegans.ttp.template.SymbolTemplateFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 18:31
 */
public class ToStringProtoNodeTransformerTest {
    @Test
    public void testTransform() {
        ProtoNode root = ConjunctionTemplateFactory.create(
                SymbolTemplateFactory.create('a').max(3),
                SymbolTemplateFactory.create('b').max(3)
        ).compile();

        String expectedResult = "\nSequence: +2\n  Literal: a\n    Loop: {1,3}\n->    Literal: a\n      Literal: b\n        Loop: {1,3}\n->        Literal: b\n          <END>\n";
        String actualResult = new ToStringProtoNodeTransformer("\n", "").transform(root);

        assertEquals(actualResult, expectedResult);
    }
}
