package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface DynamicBlock {
    Result apply(char[] buffer, int offset, int length, Result previous);
}
