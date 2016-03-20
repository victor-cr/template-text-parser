package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 19.03.2016 13:37
 */
public class Result<T extends LocalContext> {
    private static final int CONTINUE = 1;
    private static final int OK = 2;
    private static final int FAIL = 3;

    private final int code;
    private final int processed;
    private final T context;

    public Result() {
        this(0, 0, null);
    }

    private Result(int code, int processed, T context) {
        this.code = code;
        this.processed = processed;

        this.context = context;
    }

    public long getProcessed() {
        return processed;
    }

    public T getContext() {
        return context;
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

    public Result<T> more(int parsed) {
        return more(parsed, null);
    }

    public Result<T> ok(int parsed) {
        return ok(parsed, null);
    }

    public Result<T> fail(int parsed) {
        return fail(parsed, null);
    }

    public Result<T> more(int parsed, T context) {
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result<>(CONTINUE, Math.addExact(this.processed, parsed), context);
    }

    public Result<T> ok(int parsed, T context) {
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result<>(OK, Math.addExact(this.processed, parsed), context);
    }

    public Result<T> fail(int parsed, T context) {
        if (parsed < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result<>(FAIL, Math.addExact(this.processed, parsed), context);
    }

    public static <K extends LocalContext> Result<K> more(K context) {
        if (context.processed() < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result<>(CONTINUE, context.processed(), context);
    }

    public static <K extends LocalContext>Result<K> ok(K context) {
        if (context.processed() < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result<>(OK, context.processed(), context);
    }

    public static <K extends LocalContext> Result<K> fail(K context) {
        if (context.processed() < 0) {
            throw new IllegalArgumentException("Parsed characters number cannot be negative");
        }

        return new Result<>(FAIL, context.processed(), context);
    }
}
