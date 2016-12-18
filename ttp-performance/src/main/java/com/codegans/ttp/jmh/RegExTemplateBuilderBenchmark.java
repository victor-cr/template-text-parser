package com.codegans.ttp.jmh;

import com.codegans.ttp.dsl.java.RegExTemplateBuilder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 8:45
 */
@State(Scope.Benchmark)
public class RegExTemplateBuilderBenchmark {
    private final RegExTemplateBuilder builder = new RegExTemplateBuilder();

    @Benchmark
    public void simpleBuildCompile(Blackhole hole) {
        hole.consume(builder.build("abcdefjhijk").compile());
    }

    @Benchmark
    public void unboundBuildCompile(Blackhole hole) {
        hole.consume(builder.build("ab*c+d*e+f*j+h*i+j*k").compile());
    }
}
