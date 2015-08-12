package com.codegans.ttp.stream;

import com.codegans.ttp.LineStream;
import com.codegans.ttp.misc.EncodedLazyCharSequence;
import com.codegans.ttp.token.EolToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:41
 */
public abstract class AbstractLineStream implements LineStream {
    protected static final int NOT_FOUND = -1;

    protected final Logger log;
    private final AtomicLong index;
    private final EnumSet<EolToken> eolTokens;
    protected final int maxEolLength;

    private volatile CharSequence line;
    private volatile boolean exhausted;

    public AbstractLineStream(EolToken... eolTokens) {
        this.log = LoggerFactory.getLogger(getClass());
        this.index = new AtomicLong(0L);

        this.eolTokens = eolTokens == null || eolTokens.length == 0
                ? EnumSet.of(EolToken.SYSTEM)
                : EnumSet.of(eolTokens[0], eolTokens);

        this.maxEolLength = this.eolTokens.stream().mapToInt(e -> e.chars().length).max().orElse(0);

        log.debug("Create a stream with EOL terminators: {}", this.eolTokens);
    }

    @Override
    public long getCurrentLineIndex() {
        return index.get();
    }

    @Override
    public CharSequence nextLine() {
        if (exhausted) {
            return line = null;
        }

        CharSequence line = internalNextLine();

        if (line != null) {
            long i = index.incrementAndGet();

            log.debug("Read a new line #{}: {}", i, new EncodedLazyCharSequence(line));
        } else {
            exhausted = true;
        }

        this.line = line;

        return line;
    }

    @Override
    public CharSequence currentLine() {
        CharSequence line = this.line;

        if (line == null && index.get() == 0 && !exhausted) {
            line = nextLine();
        }

        return line;
    }

    protected abstract CharSequence internalNextLine();

    protected int indexOfEol(CharSequence content, int offset) {
        return indexOfAny(content, offset, eolTokens.stream().map(EolToken::chars).collect(Collectors.toList()));
    }

    /**
     * Stolen from {@code org.apache.commons.lang3.StringUtils#indexOfAny(CharSequence, CharSequence...) }
     */
    private static int indexOfAny(CharSequence str, int offset, Collection<char[]> searchChars) {
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
    private static int indexOf(CharSequence source, int sourceOffset, int sourceCount,
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
