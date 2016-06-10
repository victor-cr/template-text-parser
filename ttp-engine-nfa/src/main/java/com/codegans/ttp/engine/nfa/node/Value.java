package com.codegans.ttp.engine.nfa.node;

import com.codegans.ttp.block.Block;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:12
 */
public class Value extends Node {
    private final Block block;

    public Value(Block block) {
        this.block = block;
    }
}
