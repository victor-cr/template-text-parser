package com.codegans.ttp.error;

import java.io.IOException;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 11/08/2015 15:51
 */
public class IoParseException extends ParseException {
    public IoParseException(IOException cause) {
        super(cause);
    }

    public IoParseException(String message, IOException cause) {
        super(message, cause);
    }
}
