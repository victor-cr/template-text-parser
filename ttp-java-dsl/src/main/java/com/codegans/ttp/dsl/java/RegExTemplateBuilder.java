package com.codegans.ttp.dsl.java;

import com.codegans.ttp.pattern.ConjunctionTemplateFactory;
import com.codegans.ttp.pattern.QuantifiedTemplateFactory;
import com.codegans.ttp.pattern.SymbolTemplateFactory;
import com.codegans.ttp.pattern.TemplateFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 20:08
 */
public class RegExTemplateBuilder {
    public TemplateFactory build(String regex) {
        Context context = new Context(null);

        for (int ch : regex.chars().toArray()) {
            context = context.symbol(ch);
        }

        return ConjunctionTemplateFactory.create(context.factories);
    }

    private static class Context {
        private final Context parent;
        private final List<TemplateFactory> factories = new ArrayList<>();
        private boolean escaped;

        public Context(Context parent) {
            this.parent = parent;
        }

        public Context symbol(int codePoint) {
            if (escaped) {
                switch (codePoint) {
                    case '+':
                    case '*':
                    case '?':
                    case '{':
                    case '}':
                    case '(':
                    case ')':
                    case '\\':
                        escaped = false;
                        factories.add(SymbolTemplateFactory.create(codePoint));
                        break;
                    default:
                        factories.add(SymbolTemplateFactory.create('\\'));
                        factories.add(SymbolTemplateFactory.create(codePoint));
                }
            } else if (codePoint == '\\') {
                escaped = true;
            } else if (codePoint == '(') {
                return open();
            } else if (codePoint == ')') {
                return close();
            } else {
                switch (codePoint) {
                    case '+':
                    case '*':
                    case '?':
                        quantifier(codePoint);
                        break;
                    default:
                        factories.add(SymbolTemplateFactory.create(codePoint));
                }
            }

            return this;
        }

        public void quantifier(int quantifier) {
            if (factories.isEmpty()) {
                symbol(quantifier);
            } else {
                int index = factories.size() - 1;

                TemplateFactory factory = factories.get(index);

                switch (quantifier) {
                    case '+':
                        factory = QuantifiedTemplateFactory.some(factory);
                        break;
                    case '*':
                        factory = QuantifiedTemplateFactory.unbound(factory);
                        break;
                    case '?':
                        factory = QuantifiedTemplateFactory.maybe(factory);
                        break;
                }

                factories.set(index, factory);
            }
        }

        public Context open() {
            return new Context(this);
        }

        public Context close() {
            parent.factories.add(ConjunctionTemplateFactory.create(factories));

            return parent;
        }
    }
}
