package com.codegans.ttp.misc;

import java.util.stream.IntStream;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 11/08/2015 16:39
 */
public class EncodedLazyCharSequence implements CharSequence {
    private final CharSequence content;
    private volatile String value;

    public EncodedLazyCharSequence(CharSequence content) {
        this.content = content;
    }

    @Override
    public int length() {
        return value().length();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return value().subSequence(start, end);
    }

    @Override
    public IntStream chars() {
        return value().chars();
    }

    @Override
    public IntStream codePoints() {
        return value().codePoints();
    }

    @Override
    public char charAt(int index) {
        return value().charAt(index);
    }

    @Override
    public boolean equals(Object anObject) {
        return this == anObject || anObject instanceof EncodedLazyCharSequence && equals((EncodedLazyCharSequence) anObject);

    }

    public boolean equals(EncodedLazyCharSequence that) {
        return that == this || that != null && this.content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return value();
    }

    private String value() {
        if (value == null) {
            synchronized (content) {
                if (value == null) {
                    StringBuilder result = new StringBuilder(content.length() * 2);

                    content.chars().forEach(ch -> {
                        switch (ch) {
                            case '\r':
                                result.append("\\r");
                                break;
                            case '\n':
                                result.append("\\n");
                                break;
                            case '\t':
                                result.append("\\t");
                                break;
                            default:
                                result.append((char) ch);
                        }
                    });

                    value = result.toString();
                }
            }
        }

        return value;
    }
}
