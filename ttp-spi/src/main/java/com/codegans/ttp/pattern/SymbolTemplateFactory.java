package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:50
 */
public class SymbolTemplateFactory extends GroupTemplateFactory {
    private final TemplateInstance payload;

    private SymbolTemplateFactory(TemplateInstance payload) {
        this.payload = payload;
    }

    public static TemplateFactory create(char character) {
        if (Character.isSurrogate(character)) {
            throw new IllegalArgumentException("The character 0x" + Integer.toHexString(character) + " is part of surrogate symbol. Please use int-based constructor.");
        }

        return new SymbolTemplateFactory(new Char(character));
    }

    public static TemplateFactory create(int symbol) {
        if (Character.isSupplementaryCodePoint(symbol)) {
            return new SymbolTemplateFactory(new High(symbol));
        } else if (Character.isValidCodePoint(symbol)) {
            return new SymbolTemplateFactory(new Char((char) symbol));
        } else {
            throw new IllegalArgumentException("The code point 0x" + Integer.toHexString(symbol) + " does not exist in UTF-16 encoding.");
        }
    }

    @Override
    public TemplateInstance compile() {
        return payload;
    }

    private static class Base extends BaseTemplateInstance {
        final char ch;
        TemplateInstance next;

        Base(char ch) {
            this.ch = ch;
        }

        @Override
        public Stream<TemplateInstance> options() {
            return next == null ? Stream.empty() : Stream.of(next);
        }

        @Override
        public int closeWith(TemplateInstance closure) {
            if (next == null) {
                next = closure;
                return 1;
            }

            return 1 + next.closeWith(closure);
        }

        @Override
        public void writeTo(StringBuilder out) {
            out.append(ch);

            if (next != null) {
                next.writeTo(out);
            }
        }
    }

    private static class Char extends Base {
        Char(char ch) {
            super(ch);
        }

        @Override
        public void writeTo(StringBuilder out) {
            switch (ch) {
                case '+':
                case '*':
                case '?':
                case '{':
                case '}':
                case '(':
                case ')':
                case '|':
                case '\\':
                    out.append('\\');
            }

            super.writeTo(out);
        }
    }

    private static class Low extends Base {
        Low(char ch) {
            super(ch);
        }
    }

    private static class High extends Base {
        High(int symbol) {
            super(Character.highSurrogate(symbol));
            this.next = new Low(Character.lowSurrogate(symbol));
        }

        @Override
        public Stream<TemplateInstance> options() {
            return Stream.of(next);
        }

        @Override
        public int closeWith(TemplateInstance closure) {
            return next.closeWith(closure);
        }

        @Override
        public void writeTo(StringBuilder out) {
            out.append(ch);

            next.writeTo(out);
        }
    }
}
