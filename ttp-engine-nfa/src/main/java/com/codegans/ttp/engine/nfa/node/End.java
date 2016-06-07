package com.codegans.ttp.engine.nfa.node;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:04
 */
public class End extends Node {
    private final Node open;

    public End(Node open) {
        this.open = open;

        super.next(this);
    }

    @Override
    public void next(Node next) {
        if (next != this) {
            throw new IllegalArgumentException("End node is a terminal node");
        }
    }
}
