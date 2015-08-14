package com.codegans.ttp.block;

import com.codegans.ttp.LineStream;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.error.PrematureEndParseException;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.EmptyEvent;
import com.codegans.ttp.event.ErrorEvent;
import com.codegans.ttp.event.TextEvent;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;

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
        this(new IntolerantEventBus(NullEventBus.INTSANCE), content);
    }

    public SimpleBlock(EventBus eventBus, CharSequence content) {
        super(eventBus);

        Objects.requireNonNull(content, "Content must be defined");

        this.content = content;
    }

    @Override
    public int apply(LineStream lines, int offset) {
        if (content.length() == 0) {
            publish(new EmptyEvent(this, lines.getCurrentLineIndex(), offset));

            return offset;
        }

        int len = content.length() + offset;

        CharSequence value = lines.currentLine();

        if (offset < 0 || value.length() < len) {
            publish(new ErrorEvent(this, lines.getCurrentLineIndex(), len, new PrematureEndParseException()));
        }

        int i = offset;
        int j = content.length();
        int k = 0;

        while (--j >= 0) {
            if (value.charAt(i++) != content.charAt(k++)) {
                publish(new ErrorEvent(this, lines.getCurrentLineIndex(), i, new UnexpectedTokenParseException(content.toString())));
                return i;
            }
        }

        publish(new TextEvent(this, lines.getCurrentLineIndex(), offset, content));

        return len;
    }
}
