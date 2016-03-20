package com.codegans.ttp.dsl.java;

import com.codegans.ttp.Block;
import com.codegans.ttp.block.NewLineBlock;
import com.codegans.ttp.block.TextBlock;

import java.util.Collection;
import java.util.LinkedList;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:08
 */
public class DefaultTemplateBuilder implements TemplateBuilder {
    private final Collection<Block> blocks = new LinkedList<>();

    public DefaultTemplateBuilder eol() {
        blocks.add(new NewLineBlock());

        return this;
    }

    public DefaultTemplateBuilder text(String content) {
        blocks.add(new TextBlock(content));

        return this;
    }

    @Override
    public Block build() {
        return null;
    }
}
