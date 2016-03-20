package com.codegans.ttp;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 01.08.2015 13:48
 */
public enum Terminator {
    /**
     * MS Windows-like line separator
     */
    CRLF("\r\n"),
    /**
     * RISC OS-like line separator
     */
    LFCR("\n\r"),
    /**
     * Old MacOS-like line separator
     */
    CR("\r"),
    /**
     * Unix-like line separator
     */
    LF("\n"),
    /**
     * Platform dependent line separator
     */
    SYSTEM(System.lineSeparator());

    private final String value;

    Terminator(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public char[] chars() {
        return value.toCharArray();
    }

    public static Terminator[] any() {
        return new Terminator[]{CRLF, LFCR, CR, LF};
    }

    public static Terminator valueOf(int ordinal) {
        return values()[ordinal];
    }

    public static Terminator find(CharSequence terminator) {
        for (Terminator token : values()) {
            if (token.value.contentEquals(terminator)) {
                return token;
            }
        }

        throw new IllegalArgumentException("Cannot find a line terminator: " + terminator);
    }
}
