package com.codegans.ttp.block;

import java.util.Arrays;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 10:38
 */
public class CharsBlock extends RangedBlock {
    private final int[] dictionary;

    public CharsBlock(long minOccurs, long maxOccurs, String dictionary) {
        super(minOccurs, maxOccurs);

        if (dictionary == null || dictionary.length() == 0) {
            throw new IllegalArgumentException("Dictionary cannot be undefined or empty");
        }

        this.dictionary = dictionary.chars().distinct().sorted().toArray();

        if (this.dictionary.length != dictionary.length()) {
            throw new IllegalArgumentException("Duplicated dictionary character: " + dictionary);
        }
    }

    @Override
    protected boolean mismatched(char[] buffer, int i, long j) {
        return Arrays.binarySearch(dictionary, Character.codePointAt(buffer, i)) < 0;
    }
}
