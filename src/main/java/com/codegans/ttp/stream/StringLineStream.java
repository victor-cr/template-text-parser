package com.codegans.ttp.stream;

import com.codegans.ttp.token.EolToken;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:41
 */
public class StringLineStream extends AbstractLineStream {
    private final String content;
    private final AtomicInteger offset = new AtomicInteger();

    public StringLineStream(String content, EolToken... eolTokens) {
        super(eolTokens);

        this.content = content;
    }

    @Override
    protected CharSequence internalNextLine() {
        while (true) {
            int offset = this.offset.get();

            if (offset >= content.length()) {
                return null;
            }

            int i = indexOfEol(content, offset);

            if (i == NOT_FOUND) {
                i = content.length();
            }

            if (this.offset.compareAndSet(offset, i)) {
                return content.substring(offset, i);
            } else {
                log.debug("Lost the race for offset: {} (already {})", i, this.offset.get());
            }
        }
    }
}
