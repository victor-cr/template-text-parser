package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.TextEvent;
import com.codegans.ttp.misc.StringUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 10:38
 */
public class DictionaryBlock implements Block {
    private final Collection<char[]> dictionary;

    public DictionaryBlock(CharSequence... dictionary) {
        if (dictionary == null || dictionary.length == 0) {
            throw new IllegalArgumentException("Dictionary cannot be undefined or empty");
        }

        Collection<String> strings = Arrays.stream(dictionary).map(CharSequence::toString).sorted().collect(Collectors.toList());

        String prevValue = null;
        Iterator<String> i = strings.iterator();

        for (String value = i.next(); i.hasNext(); prevValue = value, value = i.next()) {
            if (value.equals(prevValue)) {
                throw new IllegalArgumentException("Duplicated dictionary element: " + value);
            }
        }

        this.dictionary = strings.stream().map(String::toCharArray).collect(Collectors.toList());
    }

    @Override
    public int apply(EventBus eventBus, LineStream lines, int offset) {
        Objects.requireNonNull(eventBus, "Event bus is undefined");
        Objects.requireNonNull(lines, "Line stream is undefined");

        CharSequence content = lines.currentLine();

        int i = StringUtil.startsWithLongest(content, offset, dictionary);

        if (i == StringUtil.NOT_FOUND) {
            throw new UnexpectedTokenParseException(lines.getCurrentLineIndex(), offset, dictionary.toString(), content.subSequence(offset, content.length()));
        }

        eventBus.publish(new TextEvent(this, lines.getCurrentLineIndex(), offset, content.subSequence(offset, i)));

        return i;
    }
}
