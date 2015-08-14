package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.Terminator;
import com.codegans.ttp.error.UnexpectedTokenParseException;
import com.codegans.ttp.event.ErrorEvent;
import com.codegans.ttp.event.NewLineEvent;
import com.codegans.ttp.misc.StringUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class NewLineBlock extends AbstractBlock {

    public NewLineBlock() {
    }

    public NewLineBlock(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    public int apply(LineStream lines, int offset) {
        CharSequence content = lines.currentLine();

        Collection<char[]> terminators = Arrays.stream(Terminator.values()).map(Terminator::chars).collect(Collectors.toList());
//        int maxTerminatorLength = terminators.stream().mapToInt(a -> a.length).max().orElse(0);
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
            publish(new NewLineEvent(this, lines.getCurrentLineIndex(), offset, Terminator.find(content.subSequence(offset, i))));

            return 0;
        } else {
            publish(new ErrorEvent(this, lines.getCurrentLineIndex(), offset, new UnexpectedTokenParseException(
                    Arrays.stream(Terminator.values()).map(Terminator::value).collect(Collectors.joining("', '", "one of '", "'"))
            )));

            return offset;
        }
    }
}
