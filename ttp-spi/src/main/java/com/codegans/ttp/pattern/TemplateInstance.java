package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:12
 */
public interface TemplateInstance {
    Stream<TemplateInstance> options();

    int closeWith(TemplateInstance closure);

    default void writeTo(StringBuilder out) {
        out.append(toString());
    }
}
