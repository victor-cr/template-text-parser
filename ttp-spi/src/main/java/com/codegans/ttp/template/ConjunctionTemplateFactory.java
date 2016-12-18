package com.codegans.ttp.template;

import com.codegans.ttp.pattern.ProtoNode;
import com.codegans.ttp.pattern.SequenceProtoNode;

import java.util.Arrays;
import java.util.Collection;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:50
 */
public class ConjunctionTemplateFactory extends GroupTemplateFactory {
    private final TemplateFactory[] factories;

    private ConjunctionTemplateFactory(TemplateFactory[] factories) {
        this.factories = factories;
    }

    public static TemplateFactory create(TemplateFactory... factories) {
        return create(Arrays.asList(factories));
    }

    public static TemplateFactory create(Collection<TemplateFactory> factories) {
        if (factories == null || factories.isEmpty()) {
            throw new IllegalArgumentException("Cannot be empty");
        }

        if (factories.size() == 1) {
            return factories.iterator().next();
        }

        return new ConjunctionTemplateFactory(factories.toArray(new TemplateFactory[0]));
    }

    @Override
    public ProtoNode compile() {
        ProtoNode[] chain = Arrays.stream(factories).map(TemplateFactory::compile).toArray(ProtoNode[]::new);

        int len = chain.length - 1;

        for (int i = 0; i < len; i++) {
            chain[i].closeWith(chain[i + 1]);
        }

        return new SequenceProtoNode(chain);
    }
}
