package com.codegans.ttp.template;

import com.codegans.ttp.pattern.LiteralProtoNode;
import com.codegans.ttp.pattern.ProtoNode;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:50
 */
public class SymbolTemplateFactory extends GroupTemplateFactory {
    private final int codePoint;

    private SymbolTemplateFactory(int codePoint) {
        this.codePoint = codePoint;
    }

    public static TemplateFactory create(char character) {
        if (Character.isSurrogate(character)) {
            throw new IllegalArgumentException("The character 0x" + Integer.toHexString(character) + " is part of surrogate symbol. Please use int-based factory method.");
        }

        return new SymbolTemplateFactory(character);
    }

    public static TemplateFactory create(int codePoint) {
        if (Character.isValidCodePoint(codePoint)) {
            return new SymbolTemplateFactory(codePoint);
        }

        throw new IllegalArgumentException("The code point 0x" + Integer.toHexString(codePoint) + " does not exist in UTF-16 encoding.");
    }

    @Override
    public ProtoNode compile() {
        return new LiteralProtoNode(codePoint);
    }
}
