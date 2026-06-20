package com.example.module3_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public int add(int a, int b) {
        
        logger.info("Adding numbers: {} and {}", a, b); 
        return a + b;
    }
}