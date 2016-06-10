package com.codegans.ttp.layout;

import java.util.Collection;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 07.06.2016 21:01
 */
public interface Section<A extends Section<A, B>, B extends Section<B, A>> {
    long NONE = 0;
    long ONCE = 1;
    long UNBOUNDED = Long.MAX_VALUE;

    long minOccurs();

    long maxOccurs();

    Collection<B> children();
}
