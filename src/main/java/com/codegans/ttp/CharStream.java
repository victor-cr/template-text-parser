package com.codegans.ttp;

import com.codegans.ttp.token.EolToken;

import java.util.Set;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 28.07.2015 22:51
 */
public interface CharStream {
    long getCurrentPosition();

    long getCurrentLine();

    int getCurrentOffset();

    Set<EolToken> getEolTokens();

    Token next();

    Token next(int len);

    Token rest();
}
