package com.codegans.ttp.block;

import com.codegans.ttp.LineStream;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.error.PrematureEndParserException;
import com.codegans.ttp.error.UnexpectedTokenParserException;
import com.codegans.ttp.event.ErrorEvent;
import com.codegans.ttp.event.TextEvent;

import java.util.Objects;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class SimpleBlock extends AbstractBlock {
    private final String content;

    public SimpleBlock(String content) {
        Objects.requireNonNull(content, "Content must be defined");

        this.content = content;
    }

    public SimpleBlock(String content, EventBus eventBus) {
        super(eventBus);

        Objects.requireNonNull(content, "Content must be defined");

        this.content = content;
    }

    @Override
    public int apply(LineStream lines, int offset) {
        int len = content.length() + offset;

        CharSequence value = lines.currentLine();

        if (offset < 0 || value.length() < len) {
            publish(new ErrorEvent(new PrematureEndParserException(lines.getCurrentLineIndex(), len)));
        }

        int i = offset;
        int j = content.length();
        int k = 0;

        while (--j >= 0) {
            if (value.charAt(i++) != content.charAt(k++)) {
                publish(new ErrorEvent(new UnexpectedTokenParserException(lines.getCurrentLineIndex(), i)));
                return i;
            }
        }

        publish(new TextEvent(content));

        return len;
    }
}
