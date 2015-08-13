package com.codegans.ttp.misc;

import com.codegans.ttp.Block;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.TemplateBuilder;
import com.codegans.ttp.block.GroupBlock;
import com.codegans.ttp.block.DecimalBlock;
import com.codegans.ttp.block.NewLineBlock;
import com.codegans.ttp.block.SimpleBlock;

import java.util.*;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:08
 */
public class DefaultTemplateBuilder implements TemplateBuilder {
    private final Collection<Block> blocks = new LinkedList<>();
    private EventBus eventBus = new IntolerantEventBus(NullEventBus.INTSANCE);

    private DefaultTemplateBuilder() {
    }

    public static DefaultTemplateBuilder get() {
        return new DefaultTemplateBuilder();
    }

    public static ProtoBlock<SimpleBlock> text(CharSequence pattern) {
        return builder -> new SimpleBlock(builder.eventBus, pattern);
    }

    public static ProtoBlock<DecimalBlock> decimal(boolean allowLeadingZeros) {
        return builder -> new DecimalBlock(builder.eventBus, allowLeadingZeros);
    }

    public static ProtoBlock<NewLineBlock> end() {
        return builder -> new NewLineBlock(builder.eventBus);
    }

    public <V extends Block> DefaultTemplateBuilder add(ProtoBlock<V> block) {
        blocks.add(block.create(this));

        return this;
    }

    public DefaultTemplateBuilder bus(EventBus eventBus) {
        Objects.requireNonNull(eventBus, "Event bus is undefined");

        this.eventBus = eventBus;

        return this;
    }

    public Line line() {
        return new Line(this);
    }

    @Override
    public Block build() {
        return new GroupBlock(eventBus, blocks);
    }

    private static Collection<Block> addBlocks(Line parent, Block block) {
        if (parent == null) {
            return block == null ? Collections.emptySet() : Collections.singleton(block);
        }

        if (block == null) {
            return parent.blocks;
        }

        Collection<Block> result = new ArrayList<>(parent.blocks.size() + 1);

        result.addAll(parent.blocks);
        result.add(block);

        return result;
    }

    public interface ProtoBlock<V extends Block> {
        V create(DefaultTemplateBuilder builder);
    }

    public class Line implements TemplateBuilder {
        private final DefaultTemplateBuilder parent;
        private final Collection<Block> blocks;

        private Line(DefaultTemplateBuilder parent) {
            this.parent = parent;
            this.blocks = Collections.emptySet();
        }

        private Line(Line parent, Block block) {
            this.parent = parent.parent;
            this.blocks = addBlocks(parent, block);
        }

        public Line add(Block block) {
            return new Line(this, block);
        }

        @Override
        public GroupBlock build() {
            return new GroupBlock(addBlocks(this, new NewLineBlock()));
        }
    }
}
