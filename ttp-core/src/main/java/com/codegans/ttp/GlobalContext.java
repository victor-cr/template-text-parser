package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 19:21
 */
public interface GlobalContext {
    <T extends LocalContext> T get(Block<T> block);
}