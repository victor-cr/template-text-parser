package com.codegans.ttp;

import com.codegans.ttp.event.Event;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 30.07.2015 20:23
 */
public interface EventBus {
    void publish(Event event);
}
