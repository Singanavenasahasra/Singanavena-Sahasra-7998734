package com.auth.client.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController { // Make sure the 'C' is capitalized!

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}