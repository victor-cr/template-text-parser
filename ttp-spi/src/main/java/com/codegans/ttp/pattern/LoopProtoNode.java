package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 10:31
 */
public class LoopProtoNode extends ForwardProtoNode {
    private final long min;
    private final long max;
    private final ProtoNode returnTo;

    public LoopProtoNode(ProtoNode returnTo, long min, long max) {
        this.returnTo = returnTo;
        this.min = min;
        this.max = max;
    }

    @Override
    public Stream<ProtoNode> options() {
        return Stream.concat(Stream.of(returnTo), super.options());
    }

    @Override
    public String toString() {
        return "Loop: {" + min + "," + max + "}";
    }
}
