package com.codegans.ttp.transformer;

import com.codegans.ttp.pattern.ProtoNode;

import java.util.HashSet;
import java.util.Set;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 18:16
 */
public class ToStringProtoNodeTransformer implements ProtoNodeTransformer<String> {
    private final String prefix;
    private final String suffix;

    public ToStringProtoNodeTransformer() {
        this("", "");
    }

    public ToStringProtoNodeTransformer(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public String transform(ProtoNode node) {
        StringBuilder out = new StringBuilder();

        out.append(prefix);

        transform(out, node, 0, new HashSet<>());

        out.append(suffix);

        return out.toString();
    }

    private static void transform(StringBuilder out, ProtoNode node, int level, Set<ProtoNode> processed) {
        processed.add(node);
        printNode(out, node, level);

        node.options().forEach(e -> {

            if (processed.contains(e)) {
                out.append("->");
                printNode(out, e, level);
            } else {
                transform(out, e, level + 1, processed);
            }
        });
    }

    private static void printNode(StringBuilder out, ProtoNode node, int level) {
        for (int i = 0; i < level; i++) {
            out.append("  ");
        }

        out.append(node).append('\n');
    }
}
