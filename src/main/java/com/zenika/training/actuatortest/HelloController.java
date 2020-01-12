package com.zenika.training.actuatortest;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Timer timer;
    private final Counter.Builder counter;
    private MeterRegistry registry;

    public HelloController(MeterRegistry registry) {
        this.timer = registry.timer("hello-world");
        this.counter = Counter.builder("counter");
        this.registry = registry;
    }

    @GetMapping("/hello")
    @Timed("hello-world")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/world")
    public String world() {
        throw new RuntimeException("error");
    }

    @GetMapping("/hello-world")
    @Timed("hello-world")
    public String helloworld() {
        return "Hello World ";
    }

    @GetMapping("/count")
    public String count1() {
        counter.tag("tag2", "value1").register(registry).increment();
        return "Count";
    }

    @GetMapping("/count2")
    public String count2() {
        counter.tag("tag2", "value2").register(registry).increment();
        return "Count";
    }

}
