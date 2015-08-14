package com.codegans.ttp.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * JavaDoc here
 *
 * @author id967092
 * @since 13/08/2015 20:04
 */
public class StringUtilTest {

    @Test
    public void testStartsWith() {
        int expected = 3;
        int actual = StringUtil.startsWith("Abcd", 1, new char[]{'b', 'c'});

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartsWithAny_Simple() {
        int expected = 3;
        int actual = StringUtil.startsWithLongest("Abcd", 0, Arrays.<char[]>asList(new char[]{'A', 'b', 'c'}, new char[]{'b', 'c'}));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartsWithAny_LongestLast() {
        int expected = 3;
        int actual = StringUtil.startsWithLongest("Abcd", 0, Arrays.<char[]>asList(new char[]{'A', 'b'}, new char[]{'A', 'b', 'c'}));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStartsWithAny_LongestFirst() {
        int expected = 3;
        int actual = StringUtil.startsWithLongest("Abcd", 0, Arrays.<char[]>asList(new char[]{'A', 'b', 'c'}, new char[]{'A', 'b'}));

        Assert.assertEquals(expected, actual);
    }
}
