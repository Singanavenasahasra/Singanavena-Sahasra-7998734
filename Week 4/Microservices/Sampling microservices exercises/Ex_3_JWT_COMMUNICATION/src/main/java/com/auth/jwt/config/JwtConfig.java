package com.auth.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration 
public class JwtConfig { 
    @Value("${spring.security.jwt.secret}") 
    private String secret; 
    
    public String getSecret() { 
        return secret; 
    } 
}
