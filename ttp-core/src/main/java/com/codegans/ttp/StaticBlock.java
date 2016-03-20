package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface StaticBlock<T extends LocalContext> extends Block<T> {
    long length();
}
