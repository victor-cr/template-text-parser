package com.codegans.ttp.engine.nfa.node;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:04
 */
public class Start extends Node {
    private final End closure;

    public Start() {
        this.closure = new End(this);
    }
}
