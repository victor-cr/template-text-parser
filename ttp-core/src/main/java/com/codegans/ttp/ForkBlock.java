package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface ForkBlock<T extends LocalContext> extends Block<T> {
    int forks();
}
