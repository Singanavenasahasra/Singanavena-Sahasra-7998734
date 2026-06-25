package com.library.aspect;

public class LoggingAspect {

    public void beforeMethod() {
        System.out.println("Before Method Execution");
    }

    public void afterMethod() {
        System.out.println("After Method Execution");
    }
}