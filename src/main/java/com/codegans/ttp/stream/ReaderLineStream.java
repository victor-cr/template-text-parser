package com.codegans.ttp.stream;

import com.codegans.ttp.error.IoParserException;
import com.codegans.ttp.token.EolToken;

import java.io.IOException;
import java.io.Reader;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:41
 */
public class ReaderLineStream extends AbstractLineStream {
    private static final int BUFFER_SIZE = 8192;

    private final Reader reader;
    private final char[] buf = new char[BUFFER_SIZE];
    private final StringBuilder content = new StringBuilder();
    private volatile boolean exhausted = false;

    public ReaderLineStream(Reader reader, EolToken... eolTokens) {
        super(eolTokens);

        this.reader = reader;
    }

    @Override
    protected CharSequence internalNextLine() {
        if (exhausted && content.length() == 0) {
            return null;
        }

        synchronized (buf) {
            int eol = indexOfEol(content, 0);

            while (eol == NOT_FOUND && !exhausted) {
                try {
                    int len = reader.read(buf, 0, BUFFER_SIZE);

                    if (len != BUFFER_SIZE) {
                        exhausted = true;

                        log.debug("Reader has been already exhausted. Last read char sequence length: {}", len);
                    }

                    if (len != NOT_FOUND) {
                        content.append(buf, 0, len);

                        int offset = content.length() - len - maxEolLength;

                        if (offset < 0) {
                            offset = 0;
                        }

                        eol = indexOfEol(content, offset);

                        if (!exhausted && eol == content.length()) {
                            eol = NOT_FOUND;
                        }
                    }
                } catch (IOException e) {
                    throw new IoParserException(e);
                }
            }

            if (eol == NOT_FOUND) {
                String result = content.length() == 0 ? null : content.toString();

                log.debug("Last line");

                content.setLength(0);
                content.trimToSize();

                return result;
            }

            String line = content.substring(0, eol);

            content.delete(0, eol);

            log.debug("Next line");

            return line;
        }
    }
}
