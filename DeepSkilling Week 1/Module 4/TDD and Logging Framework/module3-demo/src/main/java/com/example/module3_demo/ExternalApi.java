package com.example.module3_demo;

public interface ExternalApi {
    String getData();                   
    String getUserData(String userId);   
    void logSession(String username);    
}