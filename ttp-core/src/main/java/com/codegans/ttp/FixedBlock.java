package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface FixedBlock<T extends LocalContext> extends StaticBlock<T> {
    default long length() {
        return Math.multiplyExact(width(), height());
    }

    long width();

    long height();
}
