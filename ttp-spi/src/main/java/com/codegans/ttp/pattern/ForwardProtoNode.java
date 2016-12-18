package com.codegans.ttp.pattern;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 10:31
 */
public class ForwardProtoNode implements ProtoNode {
    private final AtomicReference<ProtoNode> next;

    public ForwardProtoNode() {
        this.next = new AtomicReference<>(TerminalProtoNode.INSTANCE);
    }

    @Override
    public Stream<ProtoNode> options() {
        return Stream.of(next.get());
    }

    @Override
    public void closeWith(ProtoNode closure) {
        if (!next.compareAndSet(TerminalProtoNode.INSTANCE, closure)) {
            next.get().closeWith(closure);
        }
    }

    @Override
    public String toString() {
        return "Forward";
    }
}
