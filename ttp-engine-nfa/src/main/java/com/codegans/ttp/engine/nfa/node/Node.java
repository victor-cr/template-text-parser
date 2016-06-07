package com.codegans.ttp.engine.nfa.node;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:04
 */
public class Node {
    private Node next;

    public Node next() {
        return next;
    }

    public void next(Node next) {
        next.next = this.next;
        this.next = next;
    }
}
