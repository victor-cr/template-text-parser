package com.codegans.ttp.engine.nfa;

import com.codegans.ttp.TemplateInstance;

import java.io.Reader;
import java.util.function.Supplier;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 17:31
 */
public class NfaTemplateInstance extends AbstractTemplateInstance implements TemplateInstance {
    public NfaTemplateInstance(Supplier<Reader> supplier) {
        super(supplier);
    }

    public NfaTemplateInstance(Supplier<Reader> supplier, int bufferSize) {
        super(supplier, bufferSize);
    }

    @Override
    public TemplateInstance withErrorTolerance() {
        return this;
    }

    @Override
    protected void process(char[] buffer, int len, long position) {
    }
}
