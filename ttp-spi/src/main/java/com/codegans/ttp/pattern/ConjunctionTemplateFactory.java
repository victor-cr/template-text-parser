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
    public TemplateInstance compile() {
        TemplateInstance[] options = Arrays.stream(factories).map(TemplateFactory::compile).toArray(TemplateInstance[]::new);

        int len = 0;
        int last = options.length - 1;

        for (int i = 0; i < last; i++) {
            len += options[i].closeWith(options[i + 1]);
        }

        return new Sequence(options[0], options[last], len);
    }

    private static final class Sequence extends BaseTemplateInstance {
        private final TemplateInstance start;
        private final TemplateInstance end;
        private final int len;

        Sequence(TemplateInstance start, TemplateInstance end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public Stream<TemplateInstance> options() {
            return Stream.of(start);
        }

        @Override
        public int closeWith(TemplateInstance closure) {
            return len + end.closeWith(closure);
        }

        @Override
        public void writeTo(StringBuilder out) {
            start.writeTo(out);
        }
    }
}
