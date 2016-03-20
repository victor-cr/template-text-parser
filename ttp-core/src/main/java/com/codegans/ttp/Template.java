package com.codegans.ttp;

import java.io.Reader;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 13.03.2016 19:20
 */
public interface Template {
    TemplateInstance create(Reader reader);
}
