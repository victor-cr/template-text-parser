package com.codegans.ttp.bbb;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:12
 */
public class NewLineBlock extends CharDictionaryBlock {
    public NewLineBlock() {
        this(1, Long.MAX_VALUE);
    }

    public NewLineBlock(long minOccurs, long maxOccurs) {
        this(minOccurs, maxOccurs, "\r\n");
    }

    public NewLineBlock(long minOccurs, long maxOccurs, String terminators) {
        super(minOccurs, maxOccurs, terminators);
    }
}
