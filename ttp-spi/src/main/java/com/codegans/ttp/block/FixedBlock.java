package com.codegans.ttp.block;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface FixedBlock extends StaticBlock {
    default long length() {
        return Math.multiplyExact(width(), height());
    }

    long width();

    long height();
}
