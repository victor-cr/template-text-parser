package com.codegans.ttp.event;

import com.codegans.ttp.Event;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 30.07.2015 20:28
 */
public class TextEvent implements Event {
    private final CharSequence content;

    public TextEvent(CharSequence content) {
        this.content = content;
    }

    public CharSequence getContent() {
        return content;
    }
}
