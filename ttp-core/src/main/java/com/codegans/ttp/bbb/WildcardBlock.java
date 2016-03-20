package com.codegans.ttp.bbb;

import com.codegans.ttp.DynamicBlock;
import com.codegans.ttp.Result;

import java.util.Arrays;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:04
 */
public class WildcardBlock implements DynamicBlock {
    private final int[] dictionary;

    public WildcardBlock(String stopAt) {
        if (stopAt == null || stopAt.length() == 0) {
            throw new IllegalArgumentException("Stop characters cannot be undefined or empty");
        }

        this.dictionary = stopAt.chars().distinct().sorted().toArray();

        if (this.dictionary.length != stopAt.length()) {
            throw new IllegalArgumentException("Duplicated stop characters: " + stopAt);
        }
    }

    @Override
    public Result apply(char[] buffer, int offset, int length, Result previous) {
        int end = offset + length;
        int i = offset;

        while (i < end) {
            if (Arrays.binarySearch(dictionary, Character.codePointAt(buffer, i++)) >= 0) {
                return previous.ok(i - offset - 1);
            }
        }

        return previous.more(i - offset);
    }
}
