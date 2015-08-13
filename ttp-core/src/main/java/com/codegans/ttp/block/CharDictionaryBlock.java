package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.event.EmptyEvent;
import com.codegans.ttp.event.TextEvent;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;
import com.codegans.ttp.misc.StringUtil;

import java.util.Arrays;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 10:38
 */
public class CharDictionaryBlock extends AbstractBlock {
    private final char[] chars;

    public CharDictionaryBlock(char... chars) {
        this(new IntolerantEventBus(NullEventBus.INTSANCE), chars);
    }

    public CharDictionaryBlock(EventBus eventBus, char... chars) {
        super(eventBus);

        this.chars = chars == null || chars.length == 0
                ? new char[0]
                : Arrays.copyOf(chars, chars.length);

        Arrays.sort(this.chars);
    }

    @Override
    public int apply(LineStream lines, int offset) {
        CharSequence content = lines.currentLine();

        for (int i = offset; i < content.length(); i++) {
            if (!accept(content.charAt(i), offset, i)) {
                complete(content, lines.getCurrentLineIndex(), offset, i);

                return i;
            }
        }

        return content.length();
    }

    protected boolean accept(char ch, int start, int end) {
        return Arrays.binarySearch(chars, ch) > StringUtil.NOT_FOUND;
    }

    protected void complete(CharSequence content, long line, int start, int end) {
        if (start != end) {
            publish(new TextEvent(this, line, start, content.subSequence(start, end)));
        } else {
            publish(new EmptyEvent(this, line, start));
        }
    }
}
