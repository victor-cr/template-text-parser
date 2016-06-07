package com.codegans.ttp.engine.nfa.node;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:04
 */
public class Begin extends Node {
    private final Node parent;
    private final End closure;

    public Begin(Node parent) {
        this.parent = parent;
        this.closure = new End(this);

        this.next(this.closure);
    }
}
