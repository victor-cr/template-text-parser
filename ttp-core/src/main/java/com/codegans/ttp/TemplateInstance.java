package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:21
 */
public interface TemplateInstance {
    TemplateInstance addListener(BlockListener listener);

    TemplateInstance withErrorTolerance(boolean errorTolerance);

    default TemplateInstance withErrorTolerance() {
        return withErrorTolerance(true);
    }

    long apply();
}
