package com.codegans.ttp.dsl.java;

import com.codegans.ttp.layout.Row;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 07.06.2016 22:04
 */
public interface RowTemplateBuilder extends SectionTemplateBuilder {
    RowTemplateBuilder next(Row column);

    ColumnTemplateBuilder child();

    ColumnTemplateBuilder end();
}
