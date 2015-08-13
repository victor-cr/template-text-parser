package com.codegans.ttp.block;

import com.codegans.ttp.EventBus;
import com.codegans.ttp.misc.IntolerantEventBus;
import com.codegans.ttp.misc.NullEventBus;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 11:47
 */
public class DecimalBlock extends CharDictionaryBlock {
    private static final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private final boolean allowLeadingZeros;

    public DecimalBlock(boolean allowLeadingZeros) {
        this(new IntolerantEventBus(NullEventBus.INTSANCE), allowLeadingZeros);
    }

    public DecimalBlock(EventBus eventBus, boolean allowLeadingZeros) {
        super(eventBus, NUMBERS);

        this.allowLeadingZeros = allowLeadingZeros;
    }

    @Override
    protected boolean accept(char ch, int start, int end) {
        return (allowLeadingZeros || start != end || ch != '0') && super.accept(ch, start, end);
    }
}
