package com.codegans.ttp.template;

import com.codegans.ttp.pattern.LoopProtoNode;
import com.codegans.ttp.pattern.MaybeProtoNode;
import com.codegans.ttp.pattern.ProtoNode;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:50
 */
public final class QuantifiedTemplateFactory implements TemplateFactory {
    private final TemplateFactory content;
    private final long min;
    private final long max;

    private QuantifiedTemplateFactory(TemplateFactory content, long min, long max) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimum occurrences cannot be negative");
        }

        if (max < 1) {
            throw new IllegalArgumentException("Maximum occurrences have to be positive");
        }

        if (max < min) {
            throw new IllegalArgumentException("Maximum occurrences cannot be less than minimum");
        }

        this.content = content;
        this.min = min;
        this.max = max;
    }


    public static TemplateFactory unbound(TemplateFactory content) {
        return new QuantifiedTemplateFactory(content, 0, Long.MAX_VALUE);
    }

    public static TemplateFactory maybe(TemplateFactory content) {
        return new QuantifiedTemplateFactory(content, 0, 1);
    }

    public static TemplateFactory some(TemplateFactory content) {
        return new QuantifiedTemplateFactory(content, 1, Long.MAX_VALUE);
    }

    public static TemplateFactory create(TemplateFactory content, long min, long max) {
        if (min == 1 && max == 1) {
            return content;
        }

        return new QuantifiedTemplateFactory(content, min, max);
    }

    @Override
    public ProtoNode compile() {
        ProtoNode content = this.content.compile();

        if (min == 0 && max == 1) {
            return new MaybeProtoNode(content);
        }

        if (min == 0) {
            ProtoNode start = new MaybeProtoNode(content);
            ProtoNode end = new LoopProtoNode(content, 1, max);

            content.closeWith(end);

            return start;
        }

        if (max == 1) {
            return content; // Should never happened
        }

        ProtoNode end = new LoopProtoNode(content, min, max);

        content.closeWith(end);

        return content;
    }

    @Override
    public TemplateFactory min(long occurrences) {
        if (occurrences == min) {
            return this;
        }

        return create(content, occurrences, max);
    }

    @Override
    public TemplateFactory max(long occurrences) {
        if (occurrences == max) {
            return this;
        }

        return create(content, min, occurrences);
    }

    @Override
    public TemplateFactory unbound() {
        if (min == 0 && max == Long.MAX_VALUE) {
            return this;
        }

        return unbound(content);
    }

    @Override
    public long min() {
        return min;
    }

    @Override
    public long max() {
        return max;
    }
}
