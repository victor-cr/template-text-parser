package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 25.07.2015 19:09
 */
public interface Block {
    int apply(EventBus eventBus, LineStream lines, int offset);
}
