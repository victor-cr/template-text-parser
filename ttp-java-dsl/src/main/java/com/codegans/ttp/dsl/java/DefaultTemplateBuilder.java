package com.codegans.ttp.dsl.java;

import com.codegans.ttp.layout.Column;
import com.codegans.ttp.layout.Row;
import com.codegans.ttp.layout.Section;
import com.codegans.ttp.template.Template;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:08
 */
public class DefaultTemplateBuilder<A extends Section<A, B>, B extends Section<B, A>> implements SectionTemplateBuilder<A, B> {
    private final B parent;
    private final Deque<A> siblings;

    private DefaultTemplateBuilder(B parent) {
        this.parent = parent;
        this.siblings = new ArrayDeque<>();
    }

    public static DefaultTemplateBuilder<Row, Column> create() {
        return new DefaultTemplateBuilder<>(new ColumnImpl(1, 1));
    }

    @Override
    public DefaultTemplateBuilder<A, B> next(A sibling) {
        siblings.offer(sibling);

        return this;
    }

    @Override
    public DefaultTemplateBuilder<B, A> push() {
        return new DefaultTemplateBuilder<>(siblings.getLast());
    }

    @Override
    public DefaultTemplateBuilder<B, A> pop() {
        return null;
    }

    @Override
    public Template build() {
        return null;
    }

    private static class SectionImpl<A extends Section<A, B>, B extends Section<B, A>> implements Section<A, B> {
        private final long minOccures;
        private final long maxOccures;
        private final Collection<B> children;

        public SectionImpl(long minOccurs, long maxOccurs) {
            this.minOccures = minOccurs;
            this.maxOccures = maxOccurs;
            this.children = new ArrayList<>();
        }

        @Override
        public long minOccurs() {
            return 0;
        }

        @Override
        public long maxOccurs() {
            return 0;
        }

        @Override
        public Collection<B> children() {
            return children;
        }
    }

    private static class RowImpl extends SectionImpl<Row, Column> implements Row {
        public RowImpl(long minOccurs, long maxOccurs) {
            super(minOccurs, maxOccurs);
        }
    }

    private static class ColumnImpl extends SectionImpl<Column, Row> implements Column {
        public ColumnImpl(long minOccurs, long maxOccurs) {
            super(minOccurs, maxOccurs);
        }
    }
}
