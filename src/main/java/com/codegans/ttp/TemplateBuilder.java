package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 25.07.2015 19:08
 */
public interface TemplateBuilder<P extends Block> {
    P build();
}
