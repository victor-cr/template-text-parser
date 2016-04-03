package com.codegans.ttp;

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
    private final long processed;

    public Result() {
        this(0, 0);
    }

    private Result(int code, long processed) {
        if (processed < 0) {
            throw new IllegalArgumentException("Processed characters number cannot be negative: " + processed);
        }

        this.code = code;
        this.processed = processed;
    }

    public long getProcessed() {
        return processed;
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

    public static Result more(long processed) {
        return new Result(CONTINUE, processed);
    }

    public static Result ok(long processed) {
        return new Result(OK, processed);
    }

    public static Result fail(long processed) {
        return new Result(FAIL, processed);
    }
}
