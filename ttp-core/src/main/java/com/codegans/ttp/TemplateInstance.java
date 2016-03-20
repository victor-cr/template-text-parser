package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:21
 */
public interface TemplateInstance {
    TemplateInstance withErrorTolerance(boolean errorTolerance);

    long apply();
}
