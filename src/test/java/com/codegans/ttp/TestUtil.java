package com.codegans.ttp;

import com.codegans.ttp.block.SimpleBlock;
import com.codegans.ttp.stream.ReaderLineStream;
import com.codegans.ttp.stream.StringLineStream;
import com.codegans.ttp.token.EolToken;

import java.io.StringReader;

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

    public static LineStream string(String content) {
        return string(content, 0);
    }

    public static LineStream string(String content, int skipLines) {
        return string(content, skipLines, EolToken.LF);
    }

    public static LineStream string(String content, int skipLines, EolToken... tokens) {
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
        return reader(content, skipLines, EolToken.LF);
    }

    public static LineStream reader(String content, int skipLines, EolToken... tokens) {
        ReaderLineStream stream = new ReaderLineStream(new StringReader(content), tokens);

        while (--skipLines >= 0) {
            stream.nextLine();
        }

        return stream;
    }
}
