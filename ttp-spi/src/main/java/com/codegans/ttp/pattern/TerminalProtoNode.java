package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 10:33
 */
public class TerminalProtoNode implements ProtoNode {
    static final TerminalProtoNode INSTANCE = new TerminalProtoNode();

    private TerminalProtoNode() {
    }

    @Override
    public Stream<ProtoNode> options() {
        return Stream.empty();
    }

    @Override
    public void closeWith(ProtoNode closure) {
    }

    @Override
    public String toString() {
        return "<END>";
    }
}
