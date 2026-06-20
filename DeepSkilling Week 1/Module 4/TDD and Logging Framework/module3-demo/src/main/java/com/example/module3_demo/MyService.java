package com.example.module3_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        logger.info("fetchData() called");
        return externalApi.getData();
    }

    public String getUserDetails(String userId) {
        logger.info("getUserDetails() called for: {}", userId);
        return externalApi.getUserData(userId);
    }

    public void recordUserSession(String username) {
        logger.info("recordUserSession() called for: {}", username);
        externalApi.logSession(username);
    }
}