package com.example.customerservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication @RestController @RequestMapping("/customers")
public class CustomerApplication {
    public static void main(String[] args) { SpringApplication.run(CustomerApplication.class, args); }
    @GetMapping("/profile") public String getCustomerProfile() { return "Customer Profile: Account Active."; }
}
