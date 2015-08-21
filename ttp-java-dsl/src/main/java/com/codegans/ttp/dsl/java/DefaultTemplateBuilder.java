package com.codegans.ttp.dsl.java;

import com.codegans.ttp.Block;
import com.codegans.ttp.block.*;

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

    public DefaultTemplateBuilder text(CharSequence content) {
        blocks.add(new SimpleBlock(content));

        return this;
    }

    public DefaultTemplateBuilder decimal() {
        return decimal(true);
    }

    public DefaultTemplateBuilder decimal(boolean allowLeadingZeros) {
        blocks.add(new DecimalBlock(1, Integer.MAX_VALUE, allowLeadingZeros));

        return this;
    }

    public DefaultTemplateBuilder chars(char... dictionary) {
        blocks.add(new CharDictionaryBlock(1, Integer.MAX_VALUE, dictionary));

        return this;
    }

    public DefaultTemplateBuilder words(CharSequence... dictionary) {
        blocks.add(new DictionaryBlock(dictionary));

        return this;
    }

    @Override
    public Block build() {
        return new GroupBlock(1, 1, blocks.toArray(new Block[blocks.size()]));
    }
}
