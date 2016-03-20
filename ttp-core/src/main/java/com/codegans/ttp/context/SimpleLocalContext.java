package com.codegans.ttp.context;

import com.codegans.ttp.LocalContext;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 20:05
 */
public class SimpleLocalContext implements LocalContext {
    private final int processed;

    public SimpleLocalContext(int processed) {
        this.processed = processed;
    }

    @Override
    public int processed() {
        return processed;
    }
}
