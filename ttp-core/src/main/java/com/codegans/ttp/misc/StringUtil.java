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

    public static int startsWithLongest(CharSequence str, int offset, Collection<char[]> searchChars) {
        if (str == null || searchChars == null || str.length() == 0 || searchChars.isEmpty()) {
            return NOT_FOUND;
        }

        int end = Integer.MIN_VALUE;
        int tmp;

        for (char[] search : searchChars) {
            if (search == null) {
                continue;
            }

            tmp = startsWith(str, offset, search);

            if (tmp == NOT_FOUND) {
                continue;
            }

            if (tmp > end) {
                end = tmp;
            }
        }

        return end == Integer.MIN_VALUE ? NOT_FOUND : end;
    }

    public static int startsWith(CharSequence source, int offset, char[] target) {
        int len = target.length;
        int i = offset;
        int j = 0;

        if (offset < 0 || (offset + len > source.length())) {
            return NOT_FOUND;
        }

        while (--len >= 0) {
            if (source.charAt(i++) != target[j++]) {
                return NOT_FOUND;
            }
        }

        return i;
    }

    public static String encodeSpecial(CharSequence content) {
        StringBuilder result = new StringBuilder(content.length() * 2);

        content.chars().forEach(ch -> {
            switch (ch) {
                case '\r':
                    result.append("\\r");
                    break;
                case '\n':
                    result.append("\\n");
                    break;
                case '\t':
                    result.append("\\t");
                    break;
                default:
                    result.append((char) ch);
            }
        });

        return result.toString();
    }
}
