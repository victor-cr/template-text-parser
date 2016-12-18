package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 10:31
 */
public class MaybeProtoNode extends ForwardProtoNode {
    private final ProtoNode branch;

    public MaybeProtoNode(ProtoNode branch) {
        this.branch = branch;
    }

    @Override
    public Stream<ProtoNode> options() {
        return Stream.concat(Stream.of(branch), super.options());
    }

    @Override
    public String toString() {
        return "Maybe";
    }
}
