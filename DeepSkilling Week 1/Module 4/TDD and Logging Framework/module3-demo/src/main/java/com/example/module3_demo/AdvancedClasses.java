package com.example.module3_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EvenChecker {
    private static final Logger logger = LoggerFactory.getLogger(EvenChecker.class);

    public boolean isEven(int number) {
        logger.info("Checking if {} is even", number);
        return number % 2 == 0;
    }
}

class ExceptionThrower {
    public void throwException() {
        throw new IllegalArgumentException("Expected exception thrown");
    }
}

class PerformanceTester {
    public void performTask() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}