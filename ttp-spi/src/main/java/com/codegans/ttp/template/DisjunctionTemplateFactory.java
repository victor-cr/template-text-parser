package com.codegans.ttp.template;

import com.codegans.ttp.pattern.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:50
 */
public class DisjunctionTemplateFactory extends GroupTemplateFactory {
    private final TemplateFactory[] factories;

    private DisjunctionTemplateFactory(TemplateFactory[] factories) {
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

        return new DisjunctionTemplateFactory(factories.toArray(new TemplateFactory[0]));
    }

    @Override
    public ProtoNode compile() {
        ProtoNode[] results = Arrays.stream(factories).map(TemplateFactory::compile).toArray(ProtoNode[]::new);

        return new ChoiceProtoNode(results);
    }
}
