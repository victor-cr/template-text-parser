package com.codegans.ttp.event;

import com.codegans.ttp.Block;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 30.07.2015 20:28
 */
public class TextEvent extends Event {
    private final CharSequence content;

    public TextEvent(Block source, long line, int offset, CharSequence content) {
        super(source, line, offset);

        this.content = content;
    }

    public CharSequence getContent() {
        return content;
    }
}
