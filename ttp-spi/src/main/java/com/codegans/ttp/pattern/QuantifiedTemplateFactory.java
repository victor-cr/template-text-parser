package com.codegans.ttp.pattern;

import java.util.stream.Stream;

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
    public TemplateInstance compile() {
        TemplateInstance content = this.content.compile();

        LoopStart start = new LoopStart(content, min);

        LoopEnd end = new LoopEnd(start, max);

        start.requireBracket = content.closeWith(end) != 1;

        return start;
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

    private static final class LoopStart extends BaseTemplateInstance {
        private final long min;
        private final TemplateInstance main;
        private TemplateInstance shortcut;
        private boolean requireBracket = false;

        LoopStart(TemplateInstance main, long min) {
            this.min = min;
            this.main = main;
        }

        @Override
        public Stream<TemplateInstance> options() {
            return min == 0 ? Stream.of(main, shortcut) : Stream.of(main);
        }

        @Override
        public int closeWith(TemplateInstance closure) {
            if (min == 0) {
                if (shortcut != null) {
                    return Math.max(shortcut.closeWith(closure), main.closeWith(closure));
                }

                shortcut = closure;
            }

            return main.closeWith(closure);
        }

        @Override
        public void writeTo(StringBuilder out) {
            if (requireBracket) {
                out.append('(');
            }

            main.writeTo(out);
        }
    }

    private static final class LoopEnd extends BaseTemplateInstance {
        private final long max;
        private final LoopStart returnTo;
        private TemplateInstance exit;

        LoopEnd(LoopStart returnTo, long max) {
            this.max = max;
            this.returnTo = returnTo;
        }

        @Override
        public Stream<TemplateInstance> options() {
            return max == 1 ? Stream.of(exit) : Stream.of(returnTo, exit);
        }

        @Override
        public int closeWith(TemplateInstance closure) {
            if (exit == null) {
                exit = closure;

                return 0;
            }

            return exit.closeWith(closure);
        }

        @Override
        public void writeTo(StringBuilder out) {
            if (returnTo.requireBracket) {
                out.append(')');
            }

            if (max == 1) {
                out.append('?');
            } else if (returnTo.min == 1 && max == Long.MAX_VALUE) {
                out.append('+');
            } else if (returnTo.min == 0 && max == Long.MAX_VALUE) {
                out.append('*');
            } else {
                out.append('{').append(returnTo.min).append(',').append(max).append('}');
            }

            if (exit != null) {
                exit.writeTo(out);
            }
        }
    }
}
