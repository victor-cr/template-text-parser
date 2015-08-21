package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.Terminator;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.NewLineEvent;
import com.codegans.ttp.misc.StringUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class NewLineBlock extends AbstractBlock {

    @Override
    public int apply(EventBus eventBus, LineStream lines, int offset) {
        Objects.requireNonNull(eventBus, "Event bus is undefined");
        Objects.requireNonNull(lines, "Line stream is undefined");

        CharSequence content = lines.currentLine();

        Collection<char[]> terminators = Arrays.stream(Terminator.values()).map(Terminator::chars).collect(Collectors.toList());
//        int maxTerminatorLength = terminators.stream().mapToInt(a -> a.length).maxOccurs().orElse(0);
        int len = content.length();

//        if (offset == len) {
//            for (int j = offset - maxTerminatorLength; j < len; j++) {
//                int i = StringUtil.startsWithLongest(content, j, terminators);
//
//                if (i != StringUtil.NOT_FOUND) {
//
//                }
//            }
//
//        }

        int i = StringUtil.startsWithLongest(content, offset, terminators);

        if (i == len) {
            eventBus.publish(new NewLineEvent(this, lines.getCurrentLineIndex(), offset, Terminator.find(content.subSequence(offset, i))));

            return 0;
        }

        throw new UnexpectedTokenParseException(lines.getCurrentLineIndex(), offset, Arrays.toString(Terminator.values()), content.subSequence(offset, content.length()));
    }
}
