package com.codegans.ttp.pattern;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 20:12
 */
public abstract class GroupTemplateFactory implements TemplateFactory {
    @Override
    public TemplateFactory min(long occurrences) {
        return QuantifiedTemplateFactory.create(this, occurrences, occurrences);
    }

    @Override
    public TemplateFactory max(long occurrences) {
        return QuantifiedTemplateFactory.create(this, 1, occurrences);
    }

    @Override
    public TemplateFactory unbound() {
        return QuantifiedTemplateFactory.unbound(this);
    }

    @Override
    public long min() {
        return 1;
    }

    @Override
    public long max() {
        return 1;
    }

    @Override
    public boolean isOptional() {
        return false;
    }

    @Override
    public boolean isMandatory() {
        return true;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public boolean isMultiple() {
        return false;
    }
}
