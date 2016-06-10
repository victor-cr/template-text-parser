package com.codegans.ttp;

import com.codegans.ttp.block.Block;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 19:21
 */
public interface GlobalContext {
    <T extends LocalContext> T get(Block block);
}
