package com.codegans.ttp.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.regex.Pattern;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 8:45
 */
@State(Scope.Benchmark)
public class PatternBenchmark {

    @Benchmark
    public void simpleBuildCompile(Blackhole hole) {
        hole.consume(Pattern.compile("abcdefjhijk"));
    }

    @Benchmark
    public void unboundBuildCompile(Blackhole hole) {
        hole.consume(Pattern.compile("ab*c+d*e+f*j+h*i+j*k"));
    }
}
