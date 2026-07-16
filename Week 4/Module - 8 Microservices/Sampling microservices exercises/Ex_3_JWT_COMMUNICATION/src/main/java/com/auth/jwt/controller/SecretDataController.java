package com.auth.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretDataController {

    @GetMapping("/secure/data")
    public String getProtectedData() {
        return "Access Granted: This is highly sensitive centralized token data.";
    }
}
