package com.codegans.ttp.block;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:04
 */
public class CharBlock extends RangedBlock {
    private final int value;

    public CharBlock(long minOccurs, long maxOccurs, char codePoint) {
        this(minOccurs, maxOccurs, (int) codePoint);
    }

    public CharBlock(long minOccurs, long maxOccurs, int codePoint) {
        super(minOccurs, maxOccurs);

        if (!Character.isDefined(codePoint)) {
            throw new IllegalArgumentException("The code point " + codePoint + " is undefined");
        }

        this.value = codePoint;
    }

    @Override
    protected boolean mismatched(char[] buffer, int i, long j) {
        return Character.codePointAt(buffer, i) != value;
    }
}
