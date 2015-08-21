package com.codegans.ttp.block;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 11:47
 */
public class DecimalBlock extends CharDictionaryBlock {
    private static final char[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private final boolean allowLeadingZeros;

    public DecimalBlock(int minOccurs, int maxOccurs, boolean allowLeadingZeros) {
        super(minOccurs, maxOccurs, NUMBERS);

        this.allowLeadingZeros = allowLeadingZeros;
    }

    @Override
    protected boolean accept(char ch, int start, int end) {
        return (allowLeadingZeros || start != end || ch != '0') && super.accept(ch, start, end);
    }
}
