package com.codegans.ttp.event;

import com.codegans.ttp.Block;
import com.codegans.ttp.Terminator;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 15:36
 */
public class NewLineEvent extends Event {
    private final Terminator token;

    public NewLineEvent(Block source, long line, int offset, Terminator token) {
        super(source, line, offset);

        this.token = token;
    }

    public Terminator getTerminator() {
        return token;
    }
}
