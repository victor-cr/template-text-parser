package com.codegans.ttp.block;

import com.codegans.ttp.Result;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface Block {
    Result apply(char[] buffer, int offset, int length);
}
