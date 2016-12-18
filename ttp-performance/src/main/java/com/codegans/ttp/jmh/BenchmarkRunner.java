package com.codegans.ttp.jmh;

import com.codegans.ttp.dsl.java.RegExTemplateBuilder;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * JavaDoc here
 *
 * @author Victor Polischuk
 * @since 18.12.2016 8:45
 */
public class BenchmarkRunner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PatternBenchmark.class.getSimpleName())
                .include(RegExTemplateBuilderBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .threads(6)
                .timeUnit(TimeUnit.MICROSECONDS)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
