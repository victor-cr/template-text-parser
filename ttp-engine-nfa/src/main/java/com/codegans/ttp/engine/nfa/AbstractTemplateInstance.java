package com.codegans.ttp.engine.nfa;

import com.codegans.ttp.BlockListener;
import com.codegans.ttp.TemplateInstance;
import com.codegans.ttp.error.IoParseException;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 17:32
 */
public abstract class AbstractTemplateInstance implements TemplateInstance {
    private final Supplier<Reader> supplier;
    private final int bufferSize;

    public AbstractTemplateInstance(Supplier<Reader> supplier) {
        this(supplier, 8196);
    }

    public AbstractTemplateInstance(Supplier<Reader> supplier, int bufferSize) {
        this.supplier = supplier;
        this.bufferSize = bufferSize;
    }

    @Override
    public AbstractTemplateInstance addListener(BlockListener listener) {
        return this;
    }

    @Override
    public AbstractTemplateInstance withErrorTolerance(boolean errorTolerance) {
        return this;
    }

    @Override
    public long apply() {
        try (Reader in = supplier.get()) {
            long size = 0;
            char[] buffer = new char[bufferSize];

            for (int len = in.read(buffer); len > 0; size += len) {
                process(buffer, len, size);
            }

            process(buffer, 0, size);

            return size;
        } catch (IOException e) {
            throw new IoParseException(e);
        }
    }

    protected abstract void process(char[] buffer, int len, long position);
}
