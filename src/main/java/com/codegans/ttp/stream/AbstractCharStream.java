package com.codegans.ttp.stream;

import com.codegans.ttp.CharStream;
import com.codegans.ttp.Token;
import com.codegans.ttp.token.CharToken;
import com.codegans.ttp.token.EmptyToken;
import com.codegans.ttp.token.EndToken;
import com.codegans.ttp.token.EolToken;
import com.codegans.ttp.token.StringToken;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:41
 */
public abstract class AbstractCharStream implements CharStream {
    private final EnumSet<EolToken> eolTokens;
    private final StringBuilder buf = new StringBuilder();
    private final Node root = new Node();

    private int position = 0;
    private int line = 0;
    private int offset = 0;

    public AbstractCharStream(EolToken... eolTokens) {
        if (eolTokens == null || eolTokens.length == 0) {
            this.eolTokens = EnumSet.of(EolToken.SYSTEM);
        } else {
            this.eolTokens = EnumSet.of(eolTokens[0], eolTokens);
        }

        this.eolTokens.forEach(eol -> root.add(eol.getTerminator(), eol));
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
    public Token next() {
        int ch = nextCharInternal();

        if (ch == -1) {
            return EndToken.INSTANCE;
        }

        position++;
        offset++;
        Node node = root.get(ch);

        if (node != null) {
            Token token = eol(node);

            if (token != null) {
                return token;
            }
        }

        return new CharToken(ch);
    }

    @Override
    public Token next(int len) {
        if (len == 0) {
            return EmptyToken.INSTANCE;
        }

        if (len == 1) {
            return next();
        }

        StringBuffer local = new StringBuffer(len);
        Token token;

        do {
            token = next();
            local.append(token.chars());
        } while (local.length() < len && !eolTokens.contains(token));

        if (local.length() == 0) {
            return token;
        }

        if (eolTokens.contains(token)) {
            local
        }

        if (local.length() > len) {
            buf.insert(0, local.subSequence(len, local.length()));
        }

        return new StringToken(local.toString());
    }

    @Override
    public Token rest() {
        StringBuffer local = new StringBuffer();
        Token token;

        do {
            token = next();
            local.append(token.chars());
        } while (!eolTokens.contains(token));

        if (local.length() > len) {
            buf.insert(0, local.subSequence(len, local.length()));
        } else if (local.length() == 0) {
            return token;
        }

        return new StringToken(local.toString());
    }

    protected abstract int nextChar();

    private int nextCharInternal() {
        if (buf.length() != 0) {
            char ch = buf.charAt(0);

            buf.deleteCharAt(0);

            return ch;
        }

        return nextChar();
    }

    private Token eol(Node node) {
        EolToken token = null;
        StringBuilder local = new StringBuilder();

        do {
            if (node.token != null) {
                token = node.token;
            }

            int ch = nextCharInternal();
            local.append(ch);
            node = node.get(ch);
        } while (node != null);

        if (token != null) {
            String terminator = token.getTerminator();

            if (local.length() != terminator.length()) {
                buf.append(local.subSequence(terminator.length() - 1, local.length()));
            }

            position += terminator.length() - 1;
            line++;
            offset = 0;

            return token;
        }

        buf.insert(0, local);

        return null;
    }

    private static final class Node {
        private final Map<Integer, Node> children = new HashMap<>();
        private EolToken token;

        public Node add(CharSequence value, EolToken token) {
            if (value == null || value.length() == 0) {
                this.token = token;

                return this;
            }

            int head = value.charAt(0);
            CharSequence tail = value.subSequence(1, value.length());

            Node current = children.computeIfAbsent(head, e -> new Node());

            return current.add(tail, token);
        }

        public Node get(int value) {
            return children.get(value);
        }
    }
}
