package com.codegans.ttp.dsl.java;

import com.codegans.ttp.block.Block;
import com.codegans.ttp.layout.Section;
import com.codegans.ttp.template.TemplateBuilder;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 07.06.2016 22:04
 */
public interface SectionTemplateBuilder<A extends Section<A, B>, B extends Section<B, A>> extends TemplateBuilder {
    SectionTemplateBuilder<A, B> add(Block block);

    SectionTemplateBuilder<A, B> next();

    SectionTemplateBuilder<B, A> push();

    SectionTemplateBuilder<B, A> pop();
}
