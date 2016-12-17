package com.codegans.ttp.pattern;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 9:33
 */
public interface TemplateFactory {
    TemplateInstance compile();

    TemplateFactory min(long occurrences);

    TemplateFactory max(long occurrences);

    TemplateFactory unbound();

    long min();

    long max();

    default boolean isOptional() {
        return min() == 0;
    }

    default boolean isMandatory() {
        return min() > 0;
    }

    default boolean isSingleton() {
        return max() == 1;
    }

    default boolean isMultiple() {
        return max() > 1;
    }

}
