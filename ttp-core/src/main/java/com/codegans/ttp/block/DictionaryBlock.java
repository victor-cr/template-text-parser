package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.EmptyEvent;
import com.codegans.ttp.event.ErrorEvent;
import com.codegans.ttp.event.TextEvent;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;
import com.codegans.ttp.misc.StringUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 10:38
 */
public class DictionaryBlock extends AbstractBlock {
    private final Collection<char[]> dictionary;

    public DictionaryBlock(CharSequence... dictionary) {
        this(new IntolerantEventBus(NullEventBus.INTSANCE), dictionary);
    }

    public DictionaryBlock(EventBus eventBus, CharSequence... dictionary) {
        super(eventBus);

        this.dictionary = Arrays.stream(dictionary).map(CharSequence::toString).map(String::toCharArray).collect(Collectors.toList());
    }

    @Override
    public int apply(LineStream lines, int offset) {
        if (dictionary.isEmpty()) {
            publish(new EmptyEvent(this, lines.getCurrentLineIndex(), offset));

            return offset;
        }

        CharSequence content = lines.currentLine();

        int i = StringUtil.startsWithLongest(content, offset, dictionary);

        if (i == StringUtil.NOT_FOUND) {
            publish(new ErrorEvent(this, lines.getCurrentLineIndex(), offset, new UnexpectedTokenParseException(
                    dictionary.stream().map(Object::toString).collect(Collectors.joining("', '", "one of '", "'"))
            )));

            return offset;
        }

        publish(new TextEvent(this, lines.getCurrentLineIndex(), offset, content.subSequence(offset, i)));

        return i;
    }
}
