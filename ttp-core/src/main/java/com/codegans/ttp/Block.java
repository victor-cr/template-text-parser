package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:07
 */
public interface Block<T extends LocalContext> {
    Result<T> apply(char[] buffer, int offset, int length, GlobalContext context);
}
