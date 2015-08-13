package com.codegans.ttp.misc;

import java.util.Collection;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 12/08/2015 13:32
 */
public final class StringUtil {
    public static final int NOT_FOUND = -1;

    public StringUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * Stolen from {@code org.apache.commons.lang3.StringUtils#indexOfAny(CharSequence, CharSequence...) }
     */
    public static int indexOfAny(CharSequence str, int offset, Collection<char[]> searchChars) {
        if (str == null || searchChars == null || str.length() == 0 || searchChars.isEmpty()) {
            return NOT_FOUND;
        }

        int begin = Integer.MAX_VALUE;
        int count = Integer.MIN_VALUE;
        int tmp, len = str.length();

        for (char[] search : searchChars) {
            if (search == null) {
                continue;
            }

            tmp = indexOf(str, 0, len, search, 0, search.length, offset);

            if (tmp == NOT_FOUND) {
                continue;
            }

            if (tmp < begin) {
                begin = tmp;
                count = search.length;
            } else if (tmp == begin && count < search.length) {
                count = search.length;
            }
        }

        return begin == Integer.MAX_VALUE ? NOT_FOUND : begin + count;
    }

    /**
     * Stolen from {@code java.lang.String#indexOf(char[], int, int, char[], int, int, int)}
     */
    @SuppressWarnings("all")
    public static int indexOf(CharSequence source, int sourceOffset, int sourceCount,
                               char[] target, int targetOffset, int targetCount,
                               int fromIndex) {
        if (fromIndex >= sourceCount) {
            return targetCount == 0 ? sourceCount : NOT_FOUND;
        }

        if (fromIndex < 0) {
            fromIndex = 0;
        }

        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + sourceCount - targetCount;

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            while (source.charAt(i) != first && ++i <= max) ;

            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source.charAt(j) == target[k]; j++, k++) ;

                if (j == end) {
                    return i - sourceOffset;
                }
            }
        }

        return NOT_FOUND;
    }

}
