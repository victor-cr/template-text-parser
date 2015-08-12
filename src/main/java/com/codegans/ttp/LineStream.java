package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:51
 */
public interface LineStream {
    long getCurrentLineIndex();

    CharSequence nextLine();

    CharSequence currentLine();
}
