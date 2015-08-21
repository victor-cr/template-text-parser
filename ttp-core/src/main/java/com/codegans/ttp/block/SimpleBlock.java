package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.TextEvent;

import java.util.Objects;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class SimpleBlock extends AbstractBlock {
    private final CharSequence content;

    public SimpleBlock(CharSequence content) {
        if (content == null || content.length() == 0) {
            throw new IllegalArgumentException("Content must be defined");
        }

        this.content = content;
    }

    @Override
    public int apply(EventBus eventBus, LineStream lines, int offset) {
        Objects.requireNonNull(eventBus, "Event bus is undefined");
        Objects.requireNonNull(lines, "Line stream is undefined");

        int len = content.length() + offset;

        CharSequence value = lines.currentLine();

        if (offset < 0 || value.length() < len) {
            throw new UnexpectedTokenParseException(lines.getCurrentLineIndex(), offset, content, value.subSequence(offset, value.length()));
        }

        int i = offset;
        int j = content.length();
        int k = 0;

        while (--j >= 0) {
            if (value.charAt(i++) != content.charAt(k++)) {
                throw new UnexpectedTokenParseException(lines.getCurrentLineIndex(), i, content.subSequence(k - 1, content.length()), value.subSequence(i - 1, i + j));
            }
        }

        eventBus.publish(new TextEvent(this, lines.getCurrentLineIndex(), offset, content));

        return len;
    }
}
