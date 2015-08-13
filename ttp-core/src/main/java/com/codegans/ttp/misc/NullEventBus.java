package com.codegans.ttp.misc;

import com.codegans.ttp.event.Event;
import com.codegans.ttp.EventBus;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 12:23
 */
public class NullEventBus implements EventBus {
    public static final NullEventBus INTSANCE = new NullEventBus();

    private NullEventBus() {
    }

    @Override
    public void publish(Event event) {
    }
}
