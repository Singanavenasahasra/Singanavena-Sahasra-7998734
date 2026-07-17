package com.example.module4_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class); 

    public static void main(String[] args) {
        logger.error("This is an error message"); 
        logger.warn("This is a warning message"); 
        
        // Parameterized tracking run
        String courseModule = "Module 4";
        int completionWeeks = 1;
        logger.info("Tracking active updates for {} across week {}", courseModule, completionWeeks); 
    }
}