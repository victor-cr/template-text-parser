package com.codegans.ttp.dsl.java;

import com.codegans.ttp.layout.Column;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 07.06.2016 22:04
 */
public interface ColumnTemplateBuilder extends SectionTemplateBuilder {
    ColumnTemplateBuilder next(Column column);

    RowTemplateBuilder child();

    RowTemplateBuilder end();
}
