package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.LineStream;
import com.codegans.ttp.event.EmptyEvent;
import com.codegans.ttp.event.NewLineEvent;
import com.codegans.ttp.misc.StringUtil;
import com.codegans.ttp.Terminator;

import java.util.Arrays;
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

        int i = StringUtil.indexOfAny(content, offset, Arrays.stream(Terminator.values()).map(Terminator::chars).collect(Collectors.toList()));

        if (i == content.length()) {
            publish(new NewLineEvent(this, lines.getCurrentLineIndex(), offset, Terminator.find(content.subSequence(offset, i))));

            lines.nextLine();

            return 0;
        } else {
            publish(new EmptyEvent(this, lines.getCurrentLineIndex(), offset));

            return offset;
        }
    }
}
