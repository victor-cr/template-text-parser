package com.codegans.ttp.pattern;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 10:31
 */
public class ChoiceProtoNode implements ProtoNode {
    private final ProtoNode[] options;

    public ChoiceProtoNode(ProtoNode[] options) {
        if (options == null || options.length < 2) {
            throw new IllegalArgumentException("Choice have to contain at least 2 nodes");
        }

        this.options = options;
    }

    @Override
    public Stream<ProtoNode> options() {
        return Arrays.stream(options);
    }

    @Override
    public void closeWith(ProtoNode closure) {
        for (ProtoNode child : options) {
            child.closeWith(closure);
        }
    }

    @Override
    public String toString() {
        return "Choice: +" + options.length;
    }
}
