package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import com.codegans.ttp.event.Event;
import com.codegans.ttp.EventBus;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public abstract class AbstractBlock implements Block {
    private final Logger log;
    private final EventBus eventBus;

    public AbstractBlock() {
        this(new IntolerantEventBus(NullEventBus.INTSANCE));
    }

    public AbstractBlock(EventBus eventBus) {
        Objects.requireNonNull(eventBus, "Event bus must be defined");

        this.log = LoggerFactory.getLogger(getClass());
        this.eventBus = eventBus;
    }

    protected void publish(Event event) {
        log.debug("Publish event: {}", event);

        eventBus.publish(event);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
