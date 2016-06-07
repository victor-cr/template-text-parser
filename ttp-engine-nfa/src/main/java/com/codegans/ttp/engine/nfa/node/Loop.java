package com.codegans.ttp.engine.nfa.node;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:12
 */
public class Loop extends Node {
    private final long min;
    private final long max;

    public Loop(long min, long max) {
        this.min = min;
        this.max = max;
    }
}
