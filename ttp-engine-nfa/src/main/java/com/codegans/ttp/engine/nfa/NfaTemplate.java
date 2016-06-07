package com.codegans.ttp.engine.nfa;

import com.codegans.ttp.Block;
import com.codegans.ttp.Template;
import com.codegans.ttp.TemplateInstance;
import com.codegans.ttp.engine.nfa.node.Node;
import com.codegans.ttp.engine.nfa.node.Start;

import java.io.Reader;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 02.06.2016 18:46
 */
public class NfaTemplate implements Template {
    private final Start start;
    private Node current;

    public NfaTemplate() {
        this.start = new Start();
    }

    public NfaTemplate next(Block block) {
        return this;
    }

    public NfaTemplate next(Block block, int occurance) {
        return this;
    }

    public NfaTemplate next(Block block, int minOccurance, int maxOccurance) {
        return this;
    }

    public NfaTemplate branch() {
        return this;
    }

    public NfaTemplate or() {
        return this;
    }

    public NfaTemplate end() {
        return this;
    }

    @Override
    public TemplateInstance create(Reader reader) {
        return null;
    }
}
