package com.example.module3_demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class OrderProcessorTest {

    @Test
    public void exercise6_loggingVerification() {
        OrderProcessor processor = new OrderProcessor();
        
        
        assertDoesNotThrow(() -> {
            processor.processOrder("ORD-9928", 250.50);
            processor.processOrder("", 0.0);
            processor.processOrder("ORD-WARN", 15000.00);
        });
    }
}