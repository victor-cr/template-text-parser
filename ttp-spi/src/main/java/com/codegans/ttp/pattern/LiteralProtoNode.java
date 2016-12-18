package com.codegans.ttp.pattern;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 16:57
 */
public class LiteralProtoNode extends ForwardProtoNode {
    private final int codePoint;

    public LiteralProtoNode(int codePoint) {
        this.codePoint = codePoint;
    }

    @Override
    public String toString() {
        return "Literal: " + String.valueOf(Character.toChars(codePoint));
    }
}
