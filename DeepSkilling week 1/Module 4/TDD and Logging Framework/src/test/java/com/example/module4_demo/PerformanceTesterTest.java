package com.example.module4_demo;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import org.junit.jupiter.api.Test;

class PerformanceTesterTest {
    private final PerformanceTester tester = new PerformanceTester();

    @Test
    void testPerformanceTimeout() {
        
        assertTimeout(Duration.ofMillis(100), () -> tester.performTask()); 
    }
}