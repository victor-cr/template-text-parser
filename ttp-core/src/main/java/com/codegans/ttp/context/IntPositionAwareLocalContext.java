package com.codegans.ttp.context;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 20.03.2016 20:05
 */
public class IntPositionAwareLocalContext extends SimpleLocalContext {
    private final int position;

    public IntPositionAwareLocalContext(int processed, int position) {
        super(processed);

        this.position = position;
    }

    public int position() {
        return position;
    }
}
