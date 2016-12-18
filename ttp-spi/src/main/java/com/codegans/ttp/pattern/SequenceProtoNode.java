package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 16:57
 */
public class SequenceProtoNode implements ProtoNode {
    private final ProtoNode[] chain;

    public SequenceProtoNode(ProtoNode[] chain) {
        if (chain == null || chain.length < 2) {
            throw new IllegalArgumentException("Sequence have to contain at least 2 nodes");
        }

        this.chain = chain;
    }

    @Override
    public Stream<ProtoNode> options() {
        return Stream.of(chain[0]);
    }

    @Override
    public void closeWith(ProtoNode closure) {
        chain[chain.length - 1].closeWith(closure);
    }

    @Override
    public String toString() {
        return "Sequence: +" + chain.length;
    }
}
