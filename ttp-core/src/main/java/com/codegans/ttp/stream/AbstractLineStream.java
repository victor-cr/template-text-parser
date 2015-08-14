package com.codegans.ttp.stream;

import com.codegans.ttp.LineStream;
import com.codegans.ttp.Terminator;
import com.codegans.ttp.misc.EncodedLazyCharSequence;
import com.codegans.ttp.misc.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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
    protected final Logger log;
    private final AtomicLong index;
    private final EnumSet<Terminator> terminators;
    protected final int maxEolLength;

    private volatile CharSequence line;
    private volatile boolean exhausted;

    public AbstractLineStream(Terminator... terminators) {
        this.log = LoggerFactory.getLogger(getClass());
        this.index = new AtomicLong(0L);

        this.terminators = terminators == null || terminators.length == 0
                ? EnumSet.of(Terminator.SYSTEM)
                : EnumSet.of(terminators[0], terminators);

        this.maxEolLength = this.terminators.stream().mapToInt(e -> e.value().length()).max().orElse(0);

        log.debug("Create a stream with EOL terminators: {}", this.terminators);
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
        Collection<char[]> terminators = this.terminators.stream().map(Terminator::chars).collect(Collectors.toList());

        int i = offset;
        int len = content.length();
        int[] firsts = terminators.stream().filter(e -> e.length != 0).mapToInt(e -> e[0]).distinct().sorted().toArray();

        while (i < len) {
            while (Arrays.binarySearch(firsts, content.charAt(i)) <= StringUtil.NOT_FOUND && ++i < len) ;

            int j = StringUtil.startsWithLongest(content, i, terminators);

            if (j != StringUtil.NOT_FOUND) {
                return j;
            }

            i++;
        }

        return StringUtil.NOT_FOUND;
    }
}
