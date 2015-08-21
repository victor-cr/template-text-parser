package com.codegans.ttp.block;

import com.codegans.ttp.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public abstract class AbstractBlock implements Block {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
