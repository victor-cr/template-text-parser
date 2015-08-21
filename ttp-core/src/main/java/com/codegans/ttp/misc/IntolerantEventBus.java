package com.codegans.ttp.misc;

import com.codegans.ttp.event.Event;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.event.ErrorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 12:14
 */
public class IntolerantEventBus implements EventBus {
    public static final IntolerantEventBus NULL = new IntolerantEventBus(NullEventBus.INTSANCE);

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EventBus underline;

    public IntolerantEventBus(EventBus underline) {
        this.underline = underline;
    }

    @Override
    public void publish(Event event) {
        if (event instanceof ErrorEvent) {
            log.error("Cannot tolerate an error. Ridicules event: {}", event);

            throw ((ErrorEvent) event).getException();
        } else {
            underline.publish(event);
        }
    }
}
