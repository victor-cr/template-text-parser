package com.codegans.ttp;

import com.codegans.ttp.block.CharDictionaryBlock;
import com.codegans.ttp.block.DictionaryBlock;
import com.codegans.ttp.block.GroupBlock;
import com.codegans.ttp.block.SimpleBlock;
import com.codegans.ttp.stream.ReaderLineStream;
import com.codegans.ttp.stream.StringLineStream;

import java.io.StringReader;
import java.util.Arrays;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 11/08/2015 21:08
 */
public abstract class TestUtil {
    public static Block simple(String pattern) {
        return new SimpleBlock(pattern);
    }

    public static Block charsOneOrMore(char... chars) {
        return chars(1, Integer.MAX_VALUE, chars);
    }

    public static Block chars(int minOccurs, int maxOccurs, char... chars) {
        return new CharDictionaryBlock(minOccurs, maxOccurs, chars);
    }

    public static Block dictionary(CharSequence... words) {
        return new DictionaryBlock(words);
    }

    public static Block group(Block... blocks) {
        return new GroupBlock(1, 1, blocks);
    }

    public static LineStream string(String content) {
        return string(content, 0);
    }

    public static LineStream string(String content, int skipLines) {
        return string(content, skipLines, Terminator.LF);
    }

    public static LineStream string(String content, int skipLines, Terminator... tokens) {
        StringLineStream stream = new StringLineStream(content, tokens);

        while (--skipLines >= 0) {
            stream.nextLine();
        }

        return stream;
    }

    public static LineStream reader(String content) {
        return reader(content, 0);
    }

    public static LineStream reader(String content, int skipLines) {
        return reader(content, skipLines, Terminator.LF);
    }

    public static LineStream reader(String content, int skipLines, Terminator... tokens) {
        ReaderLineStream stream = new ReaderLineStream(new StringReader(content), tokens);

        while (--skipLines >= 0) {
            stream.nextLine();
        }

        return stream;
    }
}
