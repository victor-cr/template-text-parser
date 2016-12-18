package com.codegans.ttp.transformer;

import com.codegans.ttp.pattern.ProtoNode;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 18:14
 */
public interface ProtoNodeTransformer<T> {
    T transform(ProtoNode node);
}
