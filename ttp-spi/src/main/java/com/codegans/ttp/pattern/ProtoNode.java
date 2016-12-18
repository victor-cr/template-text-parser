package com.codegans.ttp.pattern;

import java.util.stream.Stream;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 17.12.2016 10:12
 */
public interface ProtoNode {
    Stream<ProtoNode> options();

    void closeWith(ProtoNode closure);
}

/**
 *
// Literal characters:
//
// default:
// 	s = state(*p, NULL, NULL);
// 	push(frag(s, list1(&s->out));
// 	break;

// Concatenation:
//
// case '.':
// 	e2 = pop();
// 	e1 = pop();
// 	patch(e1.out, e2.start);
// 	push(frag(e1.start, e2.out));
// 	break;

// Alternation:
//
// case '|':
// 	e2 = pop();
// 	e1 = pop();
// 	s = state(Split, e1.start, e2.start);
// 	push(frag(s, append(e1.out, e2.out)));
// 	break;

// Zero or one:
//
// case '?':
// 	e = pop();
// 	s = state(Split, e.start, NULL);
// 	push(frag(s, append(e.out, list1(&s->out1))));
// 	break;

 Zero or more:

 case '*':
 	e = pop();
 	s = state(Split, e.start, NULL);
 	patch(e.out, s);
 	push(frag(s, list1(&s->out1)));
 	break;

// One or more:
//
// case '+':
// 	e = pop();
// 	s = state(Split, e.start, NULL);
// 	patch(e.out, s);
// 	push(frag(e.start, list1(&s->out1)));
// 	break;

 */
