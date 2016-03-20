package com.codegans.ttp;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:37
 */
public class Result {
    private static final int CONTINUE = 1;
    private static final int OK = 2;
    private static final int FAIL = 3;

    private final int code;
    private final long parsed;
    private final BitSet options;

    public Result() {
        this(0, 0, null);
    }

    private Result(int code, long parsed, BitSet options) {
        this.code = code;
        this.parsed = parsed;
        this.options = options;
    }

    public long getParsed() {
        return parsed;
    }

    public IntStream getOptions() {
        if (options == null) {
            return IntStream.empty();
        }

        return options.stream();
    }

    public boolean isSuccess() {
        return code == OK;
    }

    public boolean isFail() {
        return code == FAIL;
    }

    public boolean isContinue() {
        return code == CONTINUE;
    }

    public Result more(int parsed, int... options) {
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result(CONTINUE, Math.addExact(this.parsed, parsed), of(options));
    }

    public Result ok(int parsed, int... options) {
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result(OK, Math.addExact(this.parsed, parsed), of(options));
    }

    public Result fail(int parsed, int... options) {
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result(FAIL, Math.addExact(this.parsed, parsed), of(options));
    }

    private static BitSet of(int... options) {
        if (options == null || options.length == 0) {
            return null;
        }

        return Arrays.stream(options).collect(BitSet::new, BitSet::set, null);
    }
}
