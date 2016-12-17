package com.codegans.ttp.pattern;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 20:12
 */
public abstract class BaseTemplateInstance implements TemplateInstance {
    @Override
    public final String toString() {
        StringBuilder out = new StringBuilder();

        writeTo(out);

        return out.toString();
    }

    @Override
    public abstract void writeTo(StringBuilder out);
}
