package com.example.module3_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderProcessor {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

    public void processOrder(String orderId, double amount) {
        logger.trace("Entering processOrder method for ID: {}", orderId);
        logger.debug("Validating order parameters: amount = {}", amount);
        
        if (orderId == null || orderId.isEmpty()) {
            logger.warn("Order process attempted with an empty ID string!");
            return;
        }

        logger.info("Successfully processed order {} for amount ${}", orderId, amount);

        if (amount > 10000) {
            logger.error("High-value transaction flag tripped for order: {}", orderId);
        }
    }
}