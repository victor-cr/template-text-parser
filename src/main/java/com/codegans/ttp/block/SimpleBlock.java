package com.codegans.ttp.block;

import com.codegans.ttp.CharStream;
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
    private final CharSequence content;

    public SimpleBlock(CharSequence content) {
        Objects.requireNonNull(content, "Content must be defined");

        this.content = content;
    }

    public SimpleBlock(CharSequence content, EventBus eventBus) {
        super(eventBus);

        Objects.requireNonNull(content, "Content must be defined");

        this.content = content;
    }

    @Override
    public long apply(CharStream text) {
        long line = text.line();
        long col = text.column();
        int len = content.length();

        CharSequence value = text.next(len);

        if (value.length() < len) {
            publish(new ErrorEvent(new PrematureEndParserException(text)));
        }

        if (content.equals(value)) {
            publish(new TextEvent(content));
        } else {
            publish(new ErrorEvent(new UnexpectedTokenParserException(line, col)));
        }

        return len;
    }
}
