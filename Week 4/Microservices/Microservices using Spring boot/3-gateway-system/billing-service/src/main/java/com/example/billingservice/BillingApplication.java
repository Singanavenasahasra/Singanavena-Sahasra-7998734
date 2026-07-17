package com.example.billingservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication @RestController @RequestMapping("/billing")
public class BillingApplication {
    public static void main(String[] args) { SpringApplication.run(BillingApplication.class, args); }
    @GetMapping("/status") public String getBillingStatus() { return "Billing Service: No outstanding invoices."; }
}
