package com.example.stockservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication @EnableDiscoveryClient @RestController @RequestMapping("/stock")
public class StockServiceApplication {
    @Value("${shared-msg:Alternative Default Message}") private String centralMessage;
    public static void main(String[] args) { SpringApplication.run(StockServiceApplication.class, args); }
    @GetMapping("/status") public String getStockStatus() { return "Stock Level: Operational. Message from Config Server: " + centralMessage; }
}
