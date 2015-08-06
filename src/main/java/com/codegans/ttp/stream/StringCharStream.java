package com.codegans.ttp.stream;

import com.codegans.ttp.CharStream;
import com.codegans.ttp.Token;
import com.codegans.ttp.token.EndToken;
import com.codegans.ttp.token.EolToken;

import java.util.EnumSet;
import java.util.Set;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:41
 */
public class StringCharStream implements CharStream {
    private final String content;
    private final EnumSet<EolToken> eolTokens;

    private int position = 0;
    private int line = 0;
    private int offset = 0;

    public StringCharStream(String content, EolToken... eolTokens) {
        this.content = content;

        if (eolTokens == null || eolTokens.length == 0) {
            this.eolTokens = EnumSet.of(EolToken.SYSTEM);
        } else {
            this.eolTokens = EnumSet.of(eolTokens[0], eolTokens);
        }
    }

    @Override
    public long getCurrentPosition() {
        return position;
    }

    @Override
    public long getCurrentLine() {
        return line;
    }

    @Override
    public int getCurrentOffset() {
        return offset;
    }

    @Override
    public Set<EolToken> getEolTokens() {
        return eolTokens.clone();
    }

    @Override
    public boolean hasNext() {
        return position != content.length();
    }

    @Override
    public boolean isExhausted() {
        return position == content.length();
    }

    @Override
    public Token next() {
        if (isExhausted()) {
            return EndToken.INSTANCE;
        }

        char ch = content.charAt(position++);

        if (ch == '\r' || ch == '\n') {
            eolTokens.stream().findFirst();
        }

        return ch;
    }

    @Override
    public Token next(int len) {
        return null;
    }
}
