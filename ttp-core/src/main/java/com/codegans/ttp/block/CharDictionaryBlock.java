package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.TextEvent;
import com.codegans.ttp.misc.StringUtil;

import java.util.Arrays;
import java.util.Objects;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 10:38
 */
public class CharDictionaryBlock implements Block {
    private final int minOccurs;
    private final int maxOccurs;
    private final char[] chars;

    public CharDictionaryBlock(int minOccurs, int maxOccurs, char... chars) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException("Dictionary cannot be undefined or empty");
        }

        char[] copy = Arrays.copyOf(chars, chars.length);

        Arrays.sort(copy);

        for (int i = 1; i < copy.length; i++) {
            if (copy[i - 1] == copy[i]) {
                throw new IllegalArgumentException("Duplicated dictionary element: '" + copy[i] + "'");
            }
        }

        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
        this.chars = copy;
    }

    @Override
    public int apply(EventBus eventBus, LineStream lines, int offset) {
        Objects.requireNonNull(eventBus, "Event bus is undefined");
        Objects.requireNonNull(lines, "Line stream is undefined");

        CharSequence content = lines.currentLine();

        for (int i = offset; i < content.length(); i++) {
            if (!accept(content.charAt(i), offset, i)) {
                long line = lines.getCurrentLineIndex();

                if (i - offset < minOccurs) {
                    throw new UnexpectedTokenParseException(line, i, Arrays.toString(chars), content.subSequence(i, content.length()));
                }

                eventBus.publish(new TextEvent(this, line, i, content.subSequence(offset, i)));

                return i;
            }
        }

        return content.length();
    }

    protected boolean accept(char ch, int start, int end) {
        return end - start < maxOccurs && Arrays.binarySearch(chars, ch) > StringUtil.NOT_FOUND;
    }
}
