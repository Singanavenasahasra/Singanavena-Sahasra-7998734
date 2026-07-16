package com.example.module4_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvenChecker {
    private static final Logger logger = LoggerFactory.getLogger(EvenChecker.class);

    public boolean isEven(int number) {
        logger.info("Checking parity for number: {}", number);
        return number % 2 == 0;
    }
}
