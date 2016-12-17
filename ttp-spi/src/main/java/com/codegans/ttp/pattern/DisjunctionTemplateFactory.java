package com.codegans.ttp.pattern;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

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
    public TemplateInstance compile() {
        TemplateInstance[] results = Arrays.stream(factories).map(TemplateFactory::compile).toArray(TemplateInstance[]::new);

        return new Fork(results);
    }

    private static final class Fork extends BaseTemplateInstance {
        private final TemplateInstance[] options;

        Fork(TemplateInstance[] options) {
            this.options = options;
        }

        @Override
        public Stream<TemplateInstance> options() {
            return Arrays.stream(options);
        }

        @Override
        public int closeWith(TemplateInstance closure) {
            return options().mapToInt(e -> e.closeWith(closure)).max().orElse(0);
        }

        @Override
        public void writeTo(StringBuilder out) {
            out.append('(');

            for (TemplateInstance child : options) {
                child.writeTo(out);

                out.append('|');
            }

            out.setLength(out.length() - 1);
            out.append(')');
        }
    }
}
